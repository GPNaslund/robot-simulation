package gn222gq.model.interfaces;

import gn222gq.model.Position;
import gn222gq.model.enums.Direction;
import gn222gq.model.enums.TurnDirection;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface Robot {
  public void place(Position position, Direction direction);
  public void move();
  public void rotate(TurnDirection turnDirection);

  public Optional<String> createReport();

  public void resetPosition();
}
