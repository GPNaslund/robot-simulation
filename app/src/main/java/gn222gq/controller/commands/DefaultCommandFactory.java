package gn222gq.controller.commands;

import gn222gq.model.Position;
import gn222gq.model.enums.Direction;
import gn222gq.controller.interfaces.Command;
import gn222gq.controller.interfaces.CommandFactory;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;

import java.util.Objects;

/**
 * Default implementation of Command Factory.
 */
public class DefaultCommandFactory implements CommandFactory {
  private final Robot robot;
  private final View view;

  /**
   * Instantiates a new Default command factory.
   *
   * @param robot the robot
   * @param view  the view
   */
  public DefaultCommandFactory(Robot robot, View view) {
    Objects.requireNonNull(robot);
    Objects.requireNonNull(view);

    this.robot = robot;
    this.view = view;
  }

  @Override
  public Command createPlaceCommand(double x, double y, Direction direction) {
    Objects.requireNonNull(direction);
    return new PlaceCommand(this.robot, new Position(x, y), direction);
  }

  @Override
  public Command createMoveCommand() {
    return new MoveCommand(this.robot);
  }

  @Override
  public Command createRotateLeftCommand() {
    return new RotateLeftCommand(this.robot);
  }

  @Override
  public Command createRotateRightCommand() {
    return new RotateRightCommand(this.robot);
  }

  @Override
  public Command createReportCommand() {
    return new CreateReportCommand(this.robot, this.view);
  }
}
