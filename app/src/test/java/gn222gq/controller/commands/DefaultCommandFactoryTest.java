package gn222gq.controller.commands;

import gn222gq.controller.interfaces.Command;
import gn222gq.model.enums.Direction;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class DefaultCommandFactoryTest {
  private Robot mockRobot;
  private View mockView;

  private DefaultCommandFactory sut;

  @BeforeEach
  void setup() {
    mockRobot = mock(Robot.class);
    mockView = mock(View.class);
    sut = new DefaultCommandFactory(mockRobot, mockView);
  }

  @Test
  void shouldThrowException_OnNullConstructorArguments() {
    assertThrows(NullPointerException.class, () -> {
      new DefaultCommandFactory(null, null);
    });
  }

  @Test
  void createPlaceCommand_shouldThrowOnNullDirectionArgument() {
    assertThrows(NullPointerException.class, () -> {
      this.sut.createPlaceCommand(0.0, 0.0, null);
    });
  }

  @Test
  void createPlaceCommand_shouldReturnInstanceOfPlaceCommand() {
    Command result = this.sut.createPlaceCommand(0.0, 0.0, Direction.NORTH);
    assertInstanceOf(PlaceCommand.class, result);
  }

  @Test
  void createMoveCommand_shouldReturnInstanceOfMoveCommand() {
    Command result = this.sut.createMoveCommand();
    assertInstanceOf(MoveCommand.class, result);
  }

  @Test
  void createRotateLeftCommand_shouldReturnInstanceOfRotateLeftCommand() {
    Command result = this.sut.createRotateLeftCommand();
    assertInstanceOf(RotateLeftCommand.class, result);
  }

  @Test
  void createRotateRightCommand_shouldReturnInstanceOfRotateRightCommand() {
    Command result = this.sut.createRotateRightCommand();
    assertInstanceOf(RotateRightCommand.class, result);
  }

  @Test
  void createReportCommand_shouldReturnInstanceOfCreateReportCommand() {
    Command result = this.sut.createReportCommand();
    assertInstanceOf(CreateReportCommand.class, result);
  }
}
