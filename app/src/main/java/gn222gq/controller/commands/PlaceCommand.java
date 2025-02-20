package gn222gq.controller.commands;

import gn222gq.model.Position;
import gn222gq.model.enums.Direction;
import gn222gq.controller.interfaces.Command;
import gn222gq.model.interfaces.Robot;

import java.util.Objects;

public class PlaceCommand implements Command {
  private final Robot robot;
  private final Position position;
  private final Direction direction;

  public PlaceCommand(Robot robot, Position position, Direction direction) {
    Objects.requireNonNull(robot);
    Objects.requireNonNull(position);
    Objects.requireNonNull(direction);

    this.robot = robot;
    this.position = position;
    this.direction = direction;
  }
  @Override
  public void execute() {
    this.robot.place(this.position, this.direction);
  }
}
