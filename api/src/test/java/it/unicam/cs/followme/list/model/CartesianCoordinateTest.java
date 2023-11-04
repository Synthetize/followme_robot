package it.unicam.cs.followme.list.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CartesianCoordinateTest {

    @Test
    public void createCartesianCoordinates() {
        assertDoesNotThrow(() -> new CartesianCoordinate(1.0, 1.0));
    }

    @Test
    public void testGetX() {
        CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
        assertEquals(1.0, cartesianCoordinates.xValue());
    }

    @Test
    public void testGetY() {
        CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
        assertEquals(1, cartesianCoordinates.yValue());
    }
}
