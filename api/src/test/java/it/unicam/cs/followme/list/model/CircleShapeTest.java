package it.unicam.cs.followme.list.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleShapeTest {

    CircleShape circleShape = new CircleShape(5, "_LABEL");
    @Test
    public void shouldCreateCircleShape() {
        assertDoesNotThrow(() -> new CircleShape(5, "_LABEL"));
    }

    @Test
    public void shouldThrowExceptionWhenRadiusIsEqualOrLessThanZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> new CircleShape(0, "_LABEL"));
        assertEquals("Radius must be greater than 0", exception.getMessage());
        ArithmeticException exception2 = assertThrows(ArithmeticException.class, () -> new CircleShape(-5, "_LABEL"));
        assertEquals("Radius must be greater than 0", exception2.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenConditionLabelDoesNotStartWithUnderscore() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CircleShape(5, "LABEL"));
        assertEquals("Condition label must start with _", exception.getMessage());
    }

    @Test
    public void testGetRadius() {
        assertEquals(5, circleShape.getRadius());
    }

    @Test
    public void shouldReturnTheCorrectShapeArea() {
        assertEquals(78.53981633974483, circleShape.getShapeArea());
    }

    @Test
    public void testGetConditionLabel() {
        assertEquals("_LABEL", circleShape.getConditionLabel());
    }
}
