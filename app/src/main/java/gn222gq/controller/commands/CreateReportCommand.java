package gn222gq.controller.commands;

import gn222gq.controller.interfaces.Command;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;

import java.util.Objects;
import java.util.Optional;

/**
 * Command for creating a robot of the Robot.
 */
public class CreateReportCommand implements Command {
  private final Robot robot;
  private final View view;

  /**
   * Instantiates a new Create report command.
   *
   * @param robot the robot
   * @param view  the view
   */
  public CreateReportCommand(Robot robot, View view) {
    Objects.requireNonNull(robot);
    Objects.requireNonNull(view);

    this.robot = robot;
    this.view = view;
  }

  @Override
  public void execute() {
    Optional<String> report = this.robot.createReport();
    report.ifPresent(view::displayLine);
  }
}
