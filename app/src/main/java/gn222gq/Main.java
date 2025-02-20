package gn222gq;

import gn222gq.controller.SimulationApplication;
import gn222gq.controller.SimulationApplicationConfig;
import gn222gq.controller.TextFileInputParser;
import gn222gq.controller.interfaces.CommandFactory;
import gn222gq.controller.commands.DefaultCommandFactory;
import gn222gq.model.TableTop;
import gn222gq.model.ToyRobot;
import gn222gq.view.ConsoleView;

public class Main {
  public static void main(String[] args) {
    String inputFilePath = null;
    if (args.length > 0) {
      inputFilePath = args[0];
    }

    TableTop tableTop = new TableTop(5, 5);
    ToyRobot robot = new ToyRobot(tableTop);
    ConsoleView view = new ConsoleView();
    CommandFactory commandFactory = new DefaultCommandFactory(robot, view);
    TextFileInputParser textFileInputParser = new TextFileInputParser(commandFactory);

    SimulationApplicationConfig config = new SimulationApplicationConfig(tableTop, robot, view, inputFilePath, textFileInputParser);
    SimulationApplication app = new SimulationApplication(config);
    app.startSimulation();
  }
}