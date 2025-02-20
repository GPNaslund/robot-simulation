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
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public class SimulationApplication {
  private final SimulationApplicationConfig config;

  public SimulationApplication(SimulationApplicationConfig config) {
    Objects.requireNonNull(config);
    this.config = config;
  }

  public void startSimulation() {
    Optional<ArrayList<ArrayList<Command>>> commands = readCommands();
    commands.ifPresent(this::executeCommands);
}

  private void executeCommands(ArrayList<ArrayList<Command>> commands) {
    for (ArrayList<Command> setOfCommands : commands) {
      config.getRobot().resetPosition();
      for (Command command : setOfCommands) {
        command.execute();
      }
    }
  }

  private Optional<ArrayList<ArrayList<Command>>> readCommands() {
    String filePath = config.getFilePath();
    if (filePath == null) {
      Optional<String> newFilePath = promptForFilePathInput();
      if (newFilePath.isEmpty()) {
        return Optional.empty();
      }
      filePath = newFilePath.get();
    }

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
