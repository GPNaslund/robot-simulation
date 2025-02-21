package gn222gq.controller;

import gn222gq.controller.interfaces.InputParser;
import gn222gq.model.interfaces.BoundedSpace;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;


/**
 * Config for simulation application.
 */
public class SimulationApplicationConfig {
  // The space where the robot is acting within.
  private final BoundedSpace space;
  // The robot that performs actions.
  private final Robot robot;
  // The view for outputting info.
  private final View view;
  // The filepath to the file containing commands.
  private String filePath;
  // The input parser for parsing the file of commands into command objects.
  private InputParser inputParser;

  /**
   * Instantiates a new Simulation application config.
   *
   * @param space       the space
   * @param robot       the robot
   * @param view        the view
   * @param filePath    the file path
   * @param inputParser the input parser
   */
  public SimulationApplicationConfig(BoundedSpace space, Robot robot, View view, String filePath, InputParser inputParser) {
    this.space = space;
    this.robot = robot;
    this.view = view;
    this.filePath = filePath;
    this.inputParser = inputParser;
  }

  /**
   * Gets the bounded space.
   *
   * @return the space
   */
  public BoundedSpace getSpace() {
    return this.space;
  }

  /**
   * Gets the robot.
   *
   * @return the robot
   */
  public Robot getRobot() {
    return this.robot;
  }

  /**
   * Gets the view.
   *
   * @return the view
   */
  public View getView() {
    return this.view;
  }

  /**
   * Gets the file path.
   *
   * @return the file path
   */
  public String getFilePath() {
    return this.filePath;
  }

  /**
   * Sets the file path.
   *
   * @param newFilePath the new file path
   */
  public void setFilePath(String newFilePath) {
    this.filePath = newFilePath;
  }

  /**
   * Gets the input parser.
   *
   * @return the input parser
   */
  public InputParser getInputParser() {
    return this.inputParser;
  }
}
