package gn222gq.controller.commands;

import gn222gq.model.enums.TurnDirection;
import gn222gq.model.interfaces.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RotateRightCommandTest {
  private Robot mockRobot;
  private RotateRightCommand sut;

  @BeforeEach
  void setup() {
    mockRobot = mock(Robot.class);
    sut = new RotateRightCommand(mockRobot);
  }

  @Test
  void shouldThrowException_onNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new RotateRightCommand(null);
    });
  }

  @Test
  void execute_shouldCallRobotRotateMethodWithTurnDirectionRight() {
    this.sut.execute();
    verify(mockRobot).rotate(TurnDirection.RIGHT);
  }
}
