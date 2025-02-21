package gn222gq.controller.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for input parser.
 */
public interface InputParser {
  ArrayList<ArrayList<Command>> parseInputFile(BufferedReader reader) throws IOException;
}
