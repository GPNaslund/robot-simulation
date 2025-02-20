package gn222gq.controller;

import gn222gq.controller.interfaces.Command;
import gn222gq.controller.interfaces.CommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextFileInputParserTest {
  private Command mockCommand;
  private CommandFactory mockCommandFactory;
  private TextFileInputParser sut;

  @BeforeEach
  void setup() {
    mockCommandFactory = mock(CommandFactory.class);
    mockCommand = mock(Command.class);
    when(mockCommandFactory.createPlaceCommand(anyDouble(), anyDouble(), any())).thenReturn(mockCommand);
    when(mockCommandFactory.createMoveCommand()).thenReturn(mockCommand);
    when(mockCommandFactory.createRotateLeftCommand()).thenReturn(mockCommand);
    when(mockCommandFactory.createRotateRightCommand()).thenReturn(mockCommand);
    when(mockCommandFactory.createReportCommand()).thenReturn(mockCommand);
    sut = new TextFileInputParser(mockCommandFactory);
  }

  @Test
  void shouldThrowExceptionOnNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new TextFileInputParser(null);
    });
  }

  @Test
  void parseInputFile_shouldThrowExceptionOnNullArgument() {
    assertThrows(NullPointerException.class, () -> {
      this.sut.parseInputFile(null);
    });
  }

  @Test
  void parseInputFile_shouldCreatePlaceCommandCorrectly() throws IOException {
    String[] commands = {
            "PLACE,0,0,NORTH",
            "PLACE,0,0,INVALID",
            "PLAZE,0,0,SOUTH",
            "PLACE,1,10,EAST",
    };
    String inputString = String.join(System.lineSeparator(), commands);
    StringReader stringReader = new StringReader(inputString);
    BufferedReader reader = new BufferedReader(stringReader);

    ArrayList<ArrayList<Command>> result = this.sut.parseInputFile(reader);

    assertEquals(2, result.getFirst().size());
  }

  @Test
  void parseInputFile_shouldCreateMoveCommandCorrectly() throws IOException {
    String[] commands = {
            "MOVE",
            "M0VE",
            "MOWE",
            "MOVE",
            "MOVE",
    };
    String inputString = String.join(System.lineSeparator(), commands);
    StringReader stringReader = new StringReader(inputString);
    BufferedReader reader = new BufferedReader(stringReader);

    ArrayList<ArrayList<Command>> result = this.sut.parseInputFile(reader);
    assertEquals(3, result.getFirst().size());
  }

  @Test
  void parseInputFile_shouldCreateLeftCommandCorrectly() throws IOException {
    String[] commands = {
      "LEFT",
      "LEFT",
            "\n",
      "L3FT",
      "LEF",
      "LEFT",
      "\n",
      "LEFT",
      "LEFT",
      "LEFT",
    };

    String inputString = String.join(System.lineSeparator(), commands);
    StringReader stringReader = new StringReader(inputString);
    BufferedReader reader = new BufferedReader(stringReader);

    ArrayList<ArrayList<Command>> result = this.sut.parseInputFile(reader);
    assertEquals(2, result.getFirst().size());
    assertEquals(1, result.get(1).size());
    assertEquals(3, result.get(2).size());
  }

  @Test
  void parseInputFile_shouldCreateRightCommandCorrectly() throws IOException {
    String[] commands = {
            "RIGHT",
            "RIGHT",
            "RIGHT",
            "RIGHT",
            "\n",
            "RIGH",
            "RIGHT",
            "RIGHT",
            "\n",
            "R",
            "RIGHET",
            "RIGHT ",
    };

    String inputString = String.join(System.lineSeparator(), commands);
    StringReader stringReader = new StringReader(inputString);
    BufferedReader reader = new BufferedReader(stringReader);

    ArrayList<ArrayList<Command>> result = this.sut.parseInputFile(reader);
    assertEquals(4, result.getFirst().size());
    assertEquals(2, result.get(1).size());
    assertEquals(2, result.size());
  }

  @Test
  void parseInputFile_shouldCreateReportCommandCorrectly() throws IOException {
    String[] commands = {
            "REPORT",
            "REPORT",
            "\n",
            "REPORT",
            "\n",
            "REPORT",
            "REPORT",
            "REPORT",
            "REPORT"
    };

    String inputString = String.join(System.lineSeparator(), commands);
    StringReader stringReader = new StringReader(inputString);
    BufferedReader reader = new BufferedReader(stringReader);

    ArrayList<ArrayList<Command>> result = this.sut.parseInputFile(reader);
    assertEquals(2, result.getFirst().size());
    assertEquals(1, result.get(1).size());
    assertEquals(4, result.get(2).size());
  }
}
