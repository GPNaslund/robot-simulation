package gn222gq.controller;

import gn222gq.controller.interfaces.Command;
import gn222gq.controller.interfaces.InputParser;
import gn222gq.model.interfaces.BoundedSpace;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SimulationApplicationTest {
  private BoundedSpace mockSpace;
  private Robot mockRobot;
  private View mockView;
  private InputParser mockInputParser;
  private SimulationApplicationConfig mockConfig;
  private SimulationApplication sut;

  @BeforeEach
  void setup() {
    mockSpace = mock(BoundedSpace.class);
    mockRobot = mock(Robot.class);
    mockView = mock(View.class);
    mockInputParser = mock(InputParser.class);
    mockConfig = mock(SimulationApplicationConfig.class);
    when(mockConfig.getSpace()).thenReturn(mockSpace);
    when(mockConfig.getRobot()).thenReturn(mockRobot);
    when(mockConfig.getView()).thenReturn(mockView);
    when(mockConfig.getInputParser()).thenReturn(mockInputParser);
    sut = new SimulationApplication(mockConfig);
  }

  @Test
  void shouldThrowException_onNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new SimulationApplication(null);
    });
  }

  @Test
  void startSimulation_LoadCommandsAndExecuteThem() throws IOException, URISyntaxException {
    ArrayList<ArrayList<Command>> mockCommands = new ArrayList<>();
    Command mockCommand = mock(Command.class);
    ArrayList<Command> inner = new ArrayList<>();
    inner.add(mockCommand);
    mockCommands.add(inner);

    URL resource = getClass().getClassLoader().getResource("test-data.txt");
    Path testDataPath = Paths.get(resource.toURI());

    when(mockConfig.getFilePath()).thenReturn(String.valueOf(testDataPath));
    when(mockInputParser.parseInputFile(any())).thenReturn(mockCommands);

    this.sut.startSimulation();

    verify(mockCommand).execute();
  }

  @Test
  void startSimulation_shouldPromptUserForFilePathIfNotProvidedInArgs() throws IOException, URISyntaxException {
    ArrayList<ArrayList<Command>> mockCommands = new ArrayList<>();
    Command mockCommand = mock(Command.class);
    ArrayList<Command> inner = new ArrayList<>();
    inner.add(mockCommand);
    mockCommands.add(inner);

    URL resource = getClass().getClassLoader().getResource("test-data.txt");
    Path testDataPath = Paths.get(resource.toURI());

    when(mockConfig.getFilePath()).thenReturn(null);
    when(mockInputParser.parseInputFile(any())).thenReturn(mockCommands);
    when(mockView.getUserInput("Enter y or n: ")).thenReturn("y");
    when(mockView.getUserInput("Enter filepath: ")).thenReturn(String.valueOf(testDataPath));

    this.sut.startSimulation();
  }

  @Test
  void startSimulation_shouldExitIfUserDoesNotWantToEnterFilepathIfNotProvideInArgs() {
    when(mockConfig.getFilePath()).thenReturn(null);
    when(mockView.getUserInput("Enter y or n: ")).thenReturn("n");

    verify(mockView, times(0)).getUserInput("Enter filepath: ");
  }
}
