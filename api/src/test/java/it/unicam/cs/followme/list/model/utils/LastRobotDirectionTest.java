package it.unicam.cs.followme.list.model.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

public class LastRobotDirectionTest {
    MovementDirection lastRobotDirection;
    DecimalFormat df;
    @BeforeEach
    void setUp() {
        lastRobotDirection = new LastRobotDirection(5,7,2);
        df = new DecimalFormat("#.##");
    }

    @Test
    void shouldCreateLastRobotDirection() {
        assertDoesNotThrow(() -> new LastRobotDirection(5,7,2));
        assertDoesNotThrow(() -> new LastRobotDirection(-5,-7,2));
        assertDoesNotThrow(() -> new LastRobotDirection(5,5,0));
    }

    @Test
    void shouldNotCreateLastRobotDirection() {
        assertThrows(IllegalArgumentException.class, () -> new LastRobotDirection(5,7,-2));
    }

    @Test
    void shouldReturnTheStepValueOfEachMovement() {
        Coordinate movementDirection = lastRobotDirection.getDirection();
        assertEquals("1,16", df.format(movementDirection.getX()));
        assertEquals("1,63", df.format(movementDirection.getY()));
    }
}
