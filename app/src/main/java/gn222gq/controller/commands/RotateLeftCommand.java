package gn222gq.controller.commands;

import gn222gq.model.enums.TurnDirection;
import gn222gq.controller.interfaces.Command;
import gn222gq.model.interfaces.Robot;

import java.util.Objects;

public class RotateLeftCommand implements Command {
  private final Robot robot;

  public RotateLeftCommand(Robot robot) {
    Objects.requireNonNull(robot);
    this.robot = robot;
  }

  @Override
  public void execute() {
    this.robot.rotate(TurnDirection.LEFT);
  }
}
