package gn222gq.controller.interfaces;

import gn222gq.model.enums.Direction;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;

public interface CommandFactory {
  public Command createPlaceCommand(double x, double y, Direction direction);
  public Command createMoveCommand();
  public Command createRotateLeftCommand();
  public Command createRotateRightCommand();
  public Command createReportCommand();
}
