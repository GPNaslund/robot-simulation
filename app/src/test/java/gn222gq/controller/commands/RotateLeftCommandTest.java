package gn222gq.controller.commands;

import gn222gq.model.enums.TurnDirection;
import gn222gq.model.interfaces.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RotateLeftCommandTest {
  private Robot mockRobot;
  private RotateLeftCommand sut;

  @BeforeEach
  void setup() {
    mockRobot = mock(Robot.class);
    sut = new RotateLeftCommand(mockRobot);
  }

  @Test
  void shouldThrowException_onNullConstructorArgument() {
    assertThrows(NullPointerException.class, () -> {
      new RotateLeftCommand(null);
    });
  }

  @Test
  void execute_shouldCallRobotRotateMethodWithLeftTurnDirection() {
    this.sut.execute();
    verify(mockRobot).rotate(TurnDirection.LEFT);
  }
}
