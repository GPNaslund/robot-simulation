package gn222gq.controller.commands;

import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateReportCommandTest {
  private View mockView;
  private Robot mockRobot;
  private CreateReportCommand sut;
  @BeforeEach
  void setup() {
    mockView = mock(View.class);
    mockRobot = mock(Robot.class);
    sut = new CreateReportCommand(mockRobot, mockView);
  }

  @Test
  void shouldThrowException_OnNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new CreateReportCommand(null, null);
    });
  }

  @Test
  void execute_shouldCallRobotCreateReportMethod() {
    when(mockRobot.createReport()).thenReturn(Optional.of("Working"));
    this.sut.execute();
    verify(mockRobot).createReport();
  }

  @Test
  void execute_shouldCallViewDisplayLine_OnCreatedReport() {
    when(mockRobot.createReport()).thenReturn(Optional.of("Working"));
    this.sut.execute();
    verify(mockView).displayLine("Working");
  }

  @Test
  void execute_shouldNotCallViewDisplayLine_OnFailedReport() {
    when(mockRobot.createReport()).thenReturn(Optional.empty());
    this.sut.execute();
    verify(mockView, never()).displayLine(any());
  }
}
