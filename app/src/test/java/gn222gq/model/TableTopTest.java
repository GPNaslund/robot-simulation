package gn222gq.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableTopTest {
  private TableTop sut;

  @BeforeEach
  void setup() {
    sut = new TableTop(5.0, 5.0);
  }

  @Test
  void shouldThrowExceptionOnNegativeDimensions() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TableTop(-1.0, -2.0);
    });
  }

  @Test
  void isValidPosition_shouldThrowExceptionOnNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      this.sut.isValidPosition(null);
    });
  }

  @Test
  void isValidPosition_shouldReturnTrueIfBothValuesAreWithinBounds() {
    boolean isValid = this.sut.isValidPosition(new Position(4.0, 4.0));
    assertTrue(isValid);
  }

  @Test
  void isValidPosition_shouldReturnFalseIfOneValueIsWithinBounds() {
    boolean isValid = this.sut.isValidPosition(new Position(4.0, 5.1));
    assertFalse(isValid);
  }

  @Test
  void isValidPosition_shouldReturnFalseIfNoValuesIsWithinBounds() {
    boolean isValid = this.sut.isValidPosition(new Position(5.1, 5.1));
    assertFalse(isValid);
  }
}
