package gn222gq.model;

import gn222gq.model.enums.Direction;
import gn222gq.model.enums.TurnDirection;
import gn222gq.model.interfaces.BoundedSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ToyRobotTest {
  private Position position;
  private Direction direction;
  private BoundedSpace mockSpace;
  private ToyRobot sut;

  @BeforeEach
  void setup() {
    position = new Position(0.0, 0.0);
    direction = Direction.NORTH;
    mockSpace = mock(BoundedSpace.class);
    sut = new ToyRobot(mockSpace);
  }

  @Test
  void shouldThrowException_onNullConstructorArgument() {
    assertThrows(NullPointerException.class, () -> {
      new ToyRobot(null);
    });
  }


  @Test
  void place_shouldUpdatePositionAndDirectionOnValidPosition() {
    when(mockSpace.isValidPosition(any())).thenReturn(true);
    Position newPosition = new Position(10.0, 10.0);
    Direction newDirection = Direction.EAST;
    this.sut.place(newPosition, newDirection);

    Position setPosition = this.sut.getPosition();
    Direction setDirection = this.sut.getCurrentDirection();

    assertEquals(newPosition, setPosition);
    assertEquals(newDirection, setDirection);
  }

  @Test
  void place_shouldNotUpdatePositionAndDirectionOnInvalidPosition() {
    when(mockSpace.isValidPosition(any())).thenReturn(false);
    Position newPosition = new Position(10.0, 10.0);
    Direction newDirection = Direction.EAST;
    this.sut.place(newPosition, newDirection);

    Position setPosition = this.sut.getPosition();
    Direction setDirection = this.sut.getCurrentDirection();

    assertNotEquals(newPosition, setPosition);
    assertNotEquals(newDirection, setDirection);
  }

  @Test
  void place_shouldThrowExceptionOnNullArguments() {
    assertThrows(NullPointerException.class, () -> {
      this.sut.place(null, null);
    });
  }

  @Test
  void move_shouldChangePositionCorrectlyOnValidPositionAfterPlaced() {
    when(mockSpace.isValidPosition(any())).thenReturn(true);
    this.sut.place(this.position, this.direction);
    Position beforeMove = this.sut.getPosition();
    this.sut.move();
    Position afterMove = this.sut.getPosition();

    assertNotEquals(beforeMove, afterMove);
    assertEquals(beforeMove.x(), afterMove.x());
    assertEquals(beforeMove.y() + 1, afterMove.y());
  }

  @Test
  void move_shouldChangePositionCorrectlyBasedOnDirection() {
    when(mockSpace.isValidPosition(any())).thenReturn(true);

    // NORTH -> ADDS TO Y
    this.sut.place(new Position(0.0, 0.0), Direction.NORTH);
    this.sut.move();
    Position northMoved = this.sut.getPosition();
    assertEquals(1.0, northMoved.y());
    assertEquals(0.0, northMoved.x());

    // SOUTH -> SUBTRACTS FROM Y
    this.sut.place(new Position(1.0, 1.0), Direction.SOUTH);
    this.sut.move();
    Position southMoved = this.sut.getPosition();
    assertEquals(0.0, southMoved.y());
    assertEquals(1.0, southMoved.x());

    // EAST -> SUBTRACTS FROM X
    this.sut.place(new Position(1.0, 1.0), Direction.EAST);
    this.sut.move();
    Position eastMoved = this.sut.getPosition();
    assertEquals(1.0, eastMoved.y());
    assertEquals(0.0, eastMoved.x());

    // WEST -> ADDS TO X
    this.sut.place(new Position(0.0, 0.0), Direction.WEST);
    this.sut.move();
    Position westMoved = this.sut.getPosition();
    assertEquals(0.0, westMoved.y());
    assertEquals(1.0, westMoved.x());
  }

  @Test
  void move_shouldNotChangePositionIfNotPlaced() {
    Position beforeMove = this.sut.getPosition();
    this.sut.move();
    Position afterMove = this.sut.getPosition();

    assertNull(beforeMove);
    assertNull(afterMove);
  }

  @Test
  void move_shouldNotChangePositionIfPlacedInvalidPosition() {
    this.sut.place(this.position, this.direction);
    when(mockSpace.isValidPosition(any())).thenReturn(false);
    Position beforeMove = this.sut.getPosition();
    this.sut.move();
    Position afterMove = this.sut.getPosition();

    assertEquals(beforeMove, afterMove);
  }

  @Test
  void rotate_shouldThrowExceptionOnNullArgument() {
    assertThrows(NullPointerException.class, () -> {
      this.sut.rotate(null);
    });
  }

  @Test
  void rotate_shouldNotChangeDirectionIfNotPlaced() {
    Direction currentDirection = this.sut.getCurrentDirection();
    this.sut.rotate(TurnDirection.LEFT);
    Direction afterRotate = this.sut.getCurrentDirection();

    assertNull(currentDirection);
    assertNull(afterRotate);
  }

  @Test
  void rotate_shouldCorrectlyChangeDirectionIfPlaced() {
    when(mockSpace.isValidPosition(any())).thenReturn(true);
    this.sut.place(new Position(0.0, 0.0), Direction.NORTH);

    // LEFT TURNS
    // NORTH --> WEST
    Direction beforeTurnNorth = this.sut.getCurrentDirection();
    assertEquals(Direction.NORTH, beforeTurnNorth);
    this.sut.rotate(TurnDirection.LEFT);
    Direction afterTurnNorth = this.sut.getCurrentDirection();
    assertEquals(Direction.WEST, afterTurnNorth);
    // WEST --> SOUTH
    this.sut.rotate(TurnDirection.LEFT);
    Direction afterTurnWest = this.sut.getCurrentDirection();
    assertEquals(Direction.SOUTH, afterTurnWest);
    // SOUTH --> EAST
    this.sut.rotate(TurnDirection.LEFT);
    Direction afterTurnSouth = this.sut.getCurrentDirection();
    assertEquals(Direction.EAST, afterTurnSouth);
    // EAST --> NORTH
    this.sut.rotate(TurnDirection.LEFT);
    Direction afterTurnEast = this.sut.getCurrentDirection();
    assertEquals(Direction.NORTH, afterTurnEast);

    // RIGHT TURNS
    // NORTH --> EAST
    this.sut.rotate(TurnDirection.RIGHT);
    Direction afterRightTurnNorth = this.sut.getCurrentDirection();
    assertEquals(Direction.EAST, afterRightTurnNorth);
    // EAST --> SOUTH
    this.sut.rotate(TurnDirection.RIGHT);
    Direction afterRightTurnEast = this.sut.getCurrentDirection();
    assertEquals(Direction.SOUTH, afterRightTurnEast);
    // SOUTH --> WEST
    this.sut.rotate(TurnDirection.RIGHT);
    Direction afterRightTurnSouth = this.sut.getCurrentDirection();
    assertEquals(Direction.WEST, afterRightTurnSouth);
    // WEST --> NORTH
    this.sut.rotate(TurnDirection.RIGHT);
    Direction afterRightTurnWest = this.sut.getCurrentDirection();
    assertEquals(Direction.NORTH, afterRightTurnWest);
  }

  @Test
  void createReport_shouldNotCreateReportIfNotPlaced() {
    Optional<String> report = this.sut.createReport();
    assertTrue(report.isEmpty());
  }

  @Test
  void createReport_shouldCreateReportIfPlaced() {
    when(mockSpace.isValidPosition(any())).thenReturn(true);
    this.sut.place(new Position(1.0, 1.0), Direction.NORTH);
    Optional<String> report = this.sut.createReport();

    assertTrue(report.isPresent());
    assertEquals("1.000000, 1.000000, NORTH", report.get());
  }

  @Test
  void resetPosition_shouldSetPositionAndCurrentDirectionToNull() {
    when(mockSpace.isValidPosition(any())).thenReturn(true);
    this.sut.place(new Position(1.0, 1.0), Direction.NORTH);
    assertNotNull(this.sut.getCurrentDirection());
    assertNotNull(this.sut.getPosition());
    this.sut.resetPosition();
    assertNull(this.sut.getPosition());
    assertNull(this.sut.getCurrentDirection());
  }
}
