package gn222gq.controller.commands;

import gn222gq.controller.interfaces.Command;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;

import java.util.Objects;
import java.util.Optional;

public class CreateReportCommand implements Command {
  private final Robot robot;

  private final View view;

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
