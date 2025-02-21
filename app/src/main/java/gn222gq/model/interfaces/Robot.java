package gn222gq.model.interfaces;

import gn222gq.model.Position;
import gn222gq.model.enums.Direction;
import gn222gq.model.enums.TurnDirection;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface Robot {
  void place(Position position, Direction direction);
  void move();
  void rotate(TurnDirection turnDirection);

  Optional<String> createReport();

  void resetPosition();
}
