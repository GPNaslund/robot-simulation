package gn222gq.model.interfaces;

import gn222gq.model.Position;

/**
 * Interface representing the bounded space in which something can operate within.
 */
public interface BoundedSpace {
  /**
   * Method for checking if the provided position is within bounds of the bounded space.
   * @param pos The position to check if its within bounds.
   * @return a boolean indicating if position is valid.
   */
  boolean isValidPosition(Position pos);
}
