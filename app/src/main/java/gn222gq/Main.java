package gn222gq;

import gn222gq.controller.SimulationApplication;
import gn222gq.controller.SimulationApplicationConfig;
import gn222gq.controller.TextFileInputParser;
import gn222gq.controller.interfaces.CommandFactory;
import gn222gq.controller.commands.DefaultCommandFactory;
import gn222gq.model.TableTop;
import gn222gq.model.ToyRobot;
import gn222gq.view.ConsoleView;

import java.util.Scanner;

/**
 * The main entry point.
 */
public class Main {
  public static void main(String[] args) {

    // Checks if filepath is provided as input arg.
    String inputFilePath = null;
    if (args.length > 0) {
      inputFilePath = args[0];
    }

    // Creates instances
    TableTop tableTop = new TableTop(5, 5);
    ToyRobot robot = new ToyRobot(tableTop);
    Scanner scanner = new Scanner(System.in);
    ConsoleView view = new ConsoleView(scanner);
    CommandFactory commandFactory = new DefaultCommandFactory(robot, view);
    TextFileInputParser textFileInputParser = new TextFileInputParser(commandFactory);

    // Sets them to application config
    SimulationApplicationConfig config = new SimulationApplicationConfig(tableTop, robot, view, inputFilePath, textFileInputParser);

    // Creates and starts the simulation.
    SimulationApplication app = new SimulationApplication(config);
    app.startSimulation();
  }
}