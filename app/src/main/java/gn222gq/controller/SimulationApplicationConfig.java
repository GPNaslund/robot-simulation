package gn222gq.controller;

import gn222gq.controller.interfaces.InputParser;
import gn222gq.model.interfaces.BoundedSpace;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;

public class SimulationApplicationConfig {
  private final BoundedSpace space;
  private final Robot robot;
  private final View view;
  private String filePath;
  private InputParser inputParser;

  public SimulationApplicationConfig(BoundedSpace space, Robot robot, View view, String filePath, InputParser inputParser) {
    this.space = space;
    this.robot = robot;
    this.view = view;
    this.filePath = filePath;
    this.inputParser = inputParser;
  }

  public BoundedSpace getSpace() {
    return this.space;
  }

  public Robot getRobot() {
    return this.robot;
  }

  public View getView() {
    return this.view;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String newFilePath) {
    this.filePath = newFilePath;
  }

  public InputParser getInputParser() {
    return this.inputParser;
  }
}
