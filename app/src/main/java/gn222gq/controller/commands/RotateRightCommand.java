package gn222gq.controller.commands;

import gn222gq.model.enums.TurnDirection;
import gn222gq.controller.interfaces.Command;
import gn222gq.model.interfaces.Robot;

import java.util.Objects;

/**
 * Command for rotating the robot to the right.
 */
public class RotateRightCommand implements Command {
  private final Robot robot;

  /**
   * Instantiates a new Rotate right command.
   *
   * @param robot the robot
   */
  public RotateRightCommand(Robot robot) {
    Objects.requireNonNull(robot);
    this.robot = robot;
  }

  @Override
  public void execute() {
    this.robot.rotate(TurnDirection.RIGHT);
  }
}
