package it.unicam.cs.followme.list.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleShapeTest {
    @Test
    public void createRectangleShape() {
        assertDoesNotThrow(() -> new RectangleShape(5, 5, "_LABEL"));
    }

    @Test
    public void shouldThrowExceptionWhenWidthOrHeightAreEqualOrLessThanZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(0, 5, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception.getMessage());
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(5, 0, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception2.getMessage());
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(-5, 5, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception3.getMessage());
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(5, -5, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception4.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenConditionLabelDoesNotStartWithUnderscore() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RectangleShape(5, 5, "LABEL"));
        assertEquals("Condition label must start with _", exception.getMessage());
    }

    @Test
    public void shouldReturnTheCorrectShapeArea() {
        RectangleShape rectangleShape = new RectangleShape(5, 8, "_LABEL");
        assertEquals(40, rectangleShape.getShapeArea());
    }
}