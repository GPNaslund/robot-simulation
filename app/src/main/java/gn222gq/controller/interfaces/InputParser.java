package gn222gq.controller.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public interface InputParser {
  public ArrayList<ArrayList<Command>> parseInputFile(BufferedReader reader) throws IOException;
}
