package gn222gq.view;

import java.util.Scanner;

/**
 * Class with methods for printing to the console and getting user input from console.
 */
public class ConsoleView implements View {

  private final Scanner scanner;

  public ConsoleView(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * Displays the provided line to the console.
   * @param line the string to print.
   */
  @Override
  public void displayLine(String line) {
    nullCheckString(line, "Line to print cannot be null");
    System.out.println(line);
  }

  /**
   * Displays a prompt and gets the user input.
   * @param prompt the prompt to display.
   * @return the user input.
   */
  @Override
  public String getUserInput(String prompt) {
    nullCheckString(prompt, "The prompt for the user cannot be null");
    System.out.println(prompt);
      return scanner.nextLine();
  }

  private void nullCheckString(String variable, String message) {
    if (variable == null) {
      throw new IllegalArgumentException(message);
    }
  }
}
