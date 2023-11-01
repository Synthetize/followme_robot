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
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> new RectangleShape(0, 5, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception.getMessage());
        ArithmeticException exception2 = assertThrows(ArithmeticException.class, () -> new RectangleShape(5, 0, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception2.getMessage());
        ArithmeticException exception3 = assertThrows(ArithmeticException.class, () -> new RectangleShape(-5, 5, "_LABEL"));
        assertEquals("Width and height must be greater than 0", exception3.getMessage());
        ArithmeticException exception4 = assertThrows(ArithmeticException.class, () -> new RectangleShape(5, -5, "_LABEL"));
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