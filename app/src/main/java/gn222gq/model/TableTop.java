package gn222gq.model;

import gn222gq.model.interfaces.BoundedSpace;

public class TableTop implements BoundedSpace {
  private final double width;
  private final double height;

  public TableTop(double width, double height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and/or height cannot be null");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public boolean isValidPosition(Position pos) {
    if (pos == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    double x = pos.x();
    double y = pos.y();

    return (y >= 0 && y <= this.height) && (x >= 0 && x <= this.width);
  }
}
