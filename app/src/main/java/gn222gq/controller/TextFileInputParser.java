package gn222gq.controller;

import gn222gq.controller.interfaces.Command;
import gn222gq.controller.interfaces.CommandFactory;
import gn222gq.controller.interfaces.InputParser;
import gn222gq.model.enums.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


/**
 * Input parser that converts a text file with commands into command objects.
 */
public class TextFileInputParser implements InputParser {
  private final CommandFactory commandFactory;

  /**
   * Instantiates a new Text file input parser.
   *
   * @param commandFactory the command factory
   */
  public TextFileInputParser(CommandFactory commandFactory) {
    Objects.requireNonNull(commandFactory);

    this.commandFactory = commandFactory;
  }

  /**
   * Method for parsing input file into a list with lists of commands.
   * @param reader The bufferedReder to get lines of text from.
   * @return An list containing lists of commands.
   * @throws IOException On failure of reading file.
   */
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

  /**
   * Helper method for parsing a line containing a command.
   * @param line The text line containing a command.
   * @return
   */
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

  /**
   * Helper method for creating the place command containing poistion and direction.
   * @param splittedLine Array with strings containing the details.
   * @return An optional that might contain the place command.
   */
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
