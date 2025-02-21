package gn222gq.controller.commands;

import gn222gq.controller.interfaces.Command;
import gn222gq.model.interfaces.Robot;

import java.util.Objects;

/**
 * Command for moving the robot.
 */
public class MoveCommand implements Command {
  private final Robot robot;

  /**
   * Instantiates a new Move command.
   *
   * @param robot the robot
   */
  public MoveCommand(Robot robot) {
    Objects.requireNonNull(robot);
    this.robot = robot;
  }

  @Override
  public void execute() {
    this.robot.move();
  }

}
