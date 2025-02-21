package gn222gq.view;

/**
 * View for displaying output and getting input.
 */
public interface View {
  void displayLine(String line);
  String getUserInput(String prompt);
}