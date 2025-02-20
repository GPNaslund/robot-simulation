package gn222gq.view;

public interface View {
  public void displayLine(String line);
  public void displayLines(String[] lines);
  public void displayHeader(String header);
  public String getUserInput(String prompt);
}
