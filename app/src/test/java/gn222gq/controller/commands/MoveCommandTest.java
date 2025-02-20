package gn222gq.controller.commands;

import gn222gq.model.interfaces.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MoveCommandTest {
  private Robot mockRobot;
  private MoveCommand sut;

  @BeforeEach
  void setup() {
    mockRobot = mock(Robot.class);
    sut = new MoveCommand(mockRobot);
  }

  @Test
  void shouldThrowException_onNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new MoveCommand(null);
    });
  }

  @Test
  void execute_shouldCallRobotsMoveFunction() {
    this.sut.execute();
    verify(mockRobot).move();
  }
}
