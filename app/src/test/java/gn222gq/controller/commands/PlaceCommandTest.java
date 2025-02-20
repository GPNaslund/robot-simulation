package gn222gq.controller.commands;

import gn222gq.model.Position;
import gn222gq.model.enums.Direction;
import gn222gq.model.interfaces.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlaceCommandTest {
  private Robot mockRobot;
  private Position position = new Position(0.0, 0.0);
  private Direction direction = Direction.NORTH;
  private PlaceCommand sut;

  @BeforeEach
  void setup() {
    mockRobot = mock(Robot.class);
    sut = new PlaceCommand(mockRobot, position, direction);
  }

  @Test
  void shouldThrowException_onNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new PlaceCommand(null, null, null);
    });
  }

  @Test
  void execute_shouldCallRobotPlaceMethod() {
    this.sut.execute();
    verify(mockRobot).place(any(), any());
  }
}
