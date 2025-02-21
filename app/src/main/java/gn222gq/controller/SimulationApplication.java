package gn222gq.controller;

import gn222gq.controller.interfaces.Command;
import gn222gq.view.View;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * The main simulation application.
 */
public class SimulationApplication {
  private final SimulationApplicationConfig config;

  /**
   * Instantiates a new Simulation application.
   *
   * @param config the config
   */
  public SimulationApplication(SimulationApplicationConfig config) {
    Objects.requireNonNull(config);
    this.config = config;
  }

  /**
   * Start simulation.
   */
  public void startSimulation() {
    Optional<ArrayList<ArrayList<Command>>> commands = readCommands();
    commands.ifPresent(this::executeCommands);
}

  /**
   * Helper method for executing the parsed commands.
   *
   * @param commands list with lists of commands to execute.
   */
  private void executeCommands(ArrayList<ArrayList<Command>> commands) {
    for (ArrayList<Command> setOfCommands : commands) {
      config.getRobot().resetPosition();
      for (Command command : setOfCommands) {
        command.execute();
      }
    }
  }

  /**
   * Helper method for loading text file and getting commands from
   * input parser.
   *
   * @return Optional that might contain 2d-list with commands.
   */
  private Optional<ArrayList<ArrayList<Command>>> readCommands() {
    // Checks if filepath is provided, and prompts user if not provided.
    String filePath = config.getFilePath();
    if (filePath == null) {
      Optional<String> newFilePath = promptForFilePathInput();
      if (newFilePath.isEmpty()) {
        return Optional.empty();
      }
      filePath = newFilePath.get();
    }

    // Loads file and gets commands from input parser. Prompts user
    // for another file if provided filepath is not available.
    while (true) {
      try (FileReader fileReader = new FileReader(filePath)){
        BufferedReader bufReader = new BufferedReader(fileReader);
        return Optional.of(config.getInputParser().parseInputFile(bufReader));
      } catch (FileNotFoundException e) {
        Optional<String> newFilePath = promptForFilePathInput();
        if (newFilePath.isEmpty()) {
          return Optional.empty();
        }
        filePath = newFilePath.get();
        config.setFilePath(filePath);
      } catch (IOException e) {
        config.getView().displayLine("Failed to read from file: " + e.getMessage());
        return Optional.empty();
      }
    }
  }

  /**
   * Helper method for prompting user for file path.
   * @return Optional with user provided string.
   */
  private Optional<String> promptForFilePathInput() {
    View view = config.getView();
    view.displayLine("Could not find any file, do you want to provide one?");

    while(true) {
      String userChoice = view.getUserInput("Enter y or n: ");
      if (userChoice.equalsIgnoreCase("y")) {
        String filePath = promptForFilePath();
        return Optional.of(filePath);
      } else if (userChoice.equalsIgnoreCase("n")) {
        return Optional.empty();
      } else {
        view.displayLine("Invalid input, try again!");
      }
    }
  }

  /**
   * Helper method for prompting user for file path + validates the path.
   * @return The user provided string.
   */
  private String promptForFilePath() {
    View view = config.getView();
    while (true) {
      String userInput = view.getUserInput("Enter filepath: ");
      if (userInput.isEmpty()) {
        view.displayLine("No input was provided, try again..");
        continue;
      }
      Path path = Paths.get(userInput);
      if (Files.exists(path) && Files.isReadable(path)) {
        return userInput;
      } else {
        view.displayLine("Invalid path, try again!");
      }
    }
  }
}
