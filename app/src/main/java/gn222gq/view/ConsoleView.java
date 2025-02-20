package gn222gq.view;

import java.util.Scanner;

public class ConsoleView implements View {
  @Override
  public void displayLine(String line) {
    nullCheckString(line, "Line to print cannot be null");
    System.out.println(line);
  }

  @Override
  public void displayLines(String[] lines) {
    if (lines == null) {
      throw new IllegalArgumentException("Lines to print cannot be null");
    }

    for (String line : lines) {
      nullCheckString(line, "Line to print cannot be null");
      System.out.println(line);
    }
  }

  @Override
  public void displayHeader(String header) {
    nullCheckString(header, "Header content cannot be null");
    System.out.println("=== " + header + " ===");
  }

  @Override
  public String getUserInput(String prompt) {
    nullCheckString(prompt, "The prompt for the user cannot be null");
    Scanner scanner = new Scanner(System.in);
    System.out.println(prompt);
    String userInput = scanner.next();
    scanner.close();
    return userInput;
  }

  private void nullCheckString(String variable, String message) {
    if (variable == null) {
      throw new IllegalArgumentException(message);
    }
  }
}
