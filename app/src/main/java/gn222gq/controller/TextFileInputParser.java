package gn222gq.controller;

import gn222gq.controller.interfaces.Command;
import gn222gq.controller.interfaces.CommandFactory;
import gn222gq.controller.interfaces.InputParser;
import gn222gq.model.enums.Direction;
import gn222gq.model.interfaces.Robot;
import gn222gq.view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class TextFileInputParser implements InputParser {
  private final CommandFactory commandFactory;
  public TextFileInputParser(CommandFactory commandFactory) {
    Objects.requireNonNull(commandFactory);

    this.commandFactory = commandFactory;
  }

  @Override
  public ArrayList<ArrayList<Command>> parseInputFile(BufferedReader reader) throws IOException {
    Objects.requireNonNull(reader);

    ArrayList<ArrayList<Command>> result = new ArrayList<>();
    ArrayList<Command> currentSet = new ArrayList<>();
      String line = reader.readLine();
      while (line != null) {
        if (line.isEmpty() && !currentSet.isEmpty()) {
          result.add(currentSet);
          currentSet = new ArrayList<>();
        }
        Optional<Command> newCommand = parseLine(line);
        if (newCommand.isPresent()) {
          currentSet.add(newCommand.get());
        }
        line = reader.readLine();
      }
      reader.close();
      if (!currentSet.isEmpty()) {
        result.add(currentSet);
      }
      return result;
  }

  private Optional<Command> parseLine(String line) {
    String[] splitted = line.split(",");
    return switch (splitted[0].toLowerCase()) {
      case "place" -> createPlaceCommand(splitted);
      case "move" -> Optional.of(this.commandFactory.createMoveCommand());
      case "left" -> Optional.of(this.commandFactory.createRotateLeftCommand());
      case "right" -> Optional.of(this.commandFactory.createRotateRightCommand());
      case "report" -> Optional.of(this.commandFactory.createReportCommand());
      default -> Optional.empty();
    };
  }

  private Optional<Command> createPlaceCommand(String[] splittedLine) {
    try {
      double xPosition = Double.parseDouble(splittedLine[1]);
      double yPosition = Double.parseDouble(splittedLine[2]);
      Direction direction = Direction.valueOf(splittedLine[3].toUpperCase());
      return Optional.of(this.commandFactory.createPlaceCommand(xPosition, yPosition, direction));
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
