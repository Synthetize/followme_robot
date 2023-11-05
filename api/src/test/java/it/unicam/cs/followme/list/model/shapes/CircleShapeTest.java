package it.unicam.cs.followme.list.model.shapes;

import it.unicam.cs.followme.list.model.shapes.CircleShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleShapeTest {

    CircleShape circleShape = new CircleShape(5, "LABEL_");
    @Test
    public void shouldCreateCircleShape() {
        assertDoesNotThrow(() -> new CircleShape(5, "LABEL_"));
    }

    @Test
    public void shouldThrowExceptionWhenRadiusIsEqualOrLessThanZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CircleShape(0, "LABEL_"));
        assertEquals("Radius must be greater than 0", exception.getMessage());
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> new CircleShape(-5, "LABEL_"));
        assertEquals("Radius must be greater than 0", exception2.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenConditionLabelDoesNotEndUWithUnderscore() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CircleShape(5, "LABEL"));
        assertEquals("Condition label must end with _", exception.getMessage());
    }

    @Test
    public void testGetConditionLabel() {
        assertEquals("LABEL_", circleShape.conditionLabel());
    }
}
