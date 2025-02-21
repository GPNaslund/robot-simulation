package gn222gq.controller.interfaces;

import gn222gq.model.enums.Direction;

/**
 * Interface for command creating factory.
 */
public interface CommandFactory {

  /**
   * Method for creating a place command.
   *
   * @param x         the x coordinate
   * @param y         the y coordinate
   * @param direction the direction
   * @return the command.
   */
  Command createPlaceCommand(double x, double y, Direction direction);

  /**
   * Method for creating Move command.
   *
   * @return the command
   */
  Command createMoveCommand();

  /**
   * Method for creating rotate left command.
   *
   * @return the command
   */
  Command createRotateLeftCommand();

  /**
   * Method for creating rotate right command.
   *
   * @return the command
   */
  Command createRotateRightCommand();

  /**
   * Method for creating create report command.
   *
   * @return the command
   */
  Command createReportCommand();
}
