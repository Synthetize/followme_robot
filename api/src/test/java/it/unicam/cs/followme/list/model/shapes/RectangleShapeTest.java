package it.unicam.cs.followme.list.model.shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleShapeTest {
    @Test
    public void createRectangleShape() {
        assertDoesNotThrow(() -> new RectangleShape(5, 5, "LABEL_"));
    }

    @Test
    public void shouldThrowExceptionWhenWidthOrHeightAreEqualOrLessThanZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(0, 5, "LABEL_"));
        assertEquals("Width and height must be greater than 0", exception.getMessage());
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(5, 0, "LABEL_"));
        assertEquals("Width and height must be greater than 0", exception2.getMessage());
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(-5, 5, "LABEL_"));
        assertEquals("Width and height must be greater than 0", exception3.getMessage());
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(5, -5, "LABEL_"));
        assertEquals("Width and height must be greater than 0", exception4.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenConditionLabelDoesNotEndWithUnderscore() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(5, 5, "LABEL"));
        assertEquals("Condition label must end with _", exception.getMessage());
    }

}