package gn222gq.model;

import gn222gq.model.enums.Direction;
import gn222gq.model.enums.TurnDirection;
import gn222gq.model.interfaces.BoundedSpace;
import gn222gq.model.interfaces.Robot;

import java.util.Objects;
import java.util.Optional;

public class ToyRobot implements Robot {
  private Position position;
  private Direction currentDirection;

  private final BoundedSpace space;

  public ToyRobot(BoundedSpace space) {
    Objects.requireNonNull(space);
    this.position = null;
    this.currentDirection = null;
    this.space = space;
  }

  public Position getPosition() {
    return this.position;
  }

  public Direction getCurrentDirection() {
    return this.currentDirection;
  }

  @Override
  public void place(Position position, Direction direction) {
    Objects.requireNonNull(position);
    Objects.requireNonNull(direction);

    if (this.space.isValidPosition(position)) {
      this.position = position;
      this.currentDirection = direction;
    }
  }

  @Override
  public void move() {
    if (this.hasBeenPlaced()) {
      Position futurePosition = switch (this.currentDirection) {
        case NORTH -> new Position(this.position.x(), this.position.y() + 1);
        case SOUTH -> new Position(this.position.x(), this.position.y() - 1);
        case EAST -> new Position(this.position.x() - 1, this.position.y());
        case WEST -> new Position(this.position.x() + 1, this.position.y());
      };
      if (this.space.isValidPosition(futurePosition)) {
        this.position = futurePosition;
      }
    }
  }

  @Override
  public void rotate(TurnDirection turnDirection) {
    Objects.requireNonNull(turnDirection);

    if (this.hasBeenPlaced()) {
      switch (currentDirection) {
        case NORTH:
          this.currentDirection = turnDirection == TurnDirection.LEFT ? Direction.WEST : Direction.EAST;
          break;
        case SOUTH:
          this.currentDirection = turnDirection == TurnDirection.LEFT ? Direction.EAST : Direction.WEST;
          break;
        case EAST:
          this.currentDirection = turnDirection == TurnDirection.LEFT ? Direction.NORTH : Direction.SOUTH;
          break;
        case WEST:
          this.currentDirection = turnDirection == TurnDirection.LEFT ? Direction.SOUTH : Direction.NORTH;
      }
    }
  }

   @Override
   public Optional<String> createReport() {
    if (this.hasBeenPlaced()) {
      return Optional.of(String.format("%f, %f, %s", this.position.x(), this.position.y(), this.currentDirection.toString()));
    }
    return Optional.empty();
   }

   @Override
   public void resetPosition() {
    this.position = null;
    this.currentDirection = null;
   }

  private boolean hasBeenPlaced() {
    return this.position != null && this.currentDirection != null;
  }
}
