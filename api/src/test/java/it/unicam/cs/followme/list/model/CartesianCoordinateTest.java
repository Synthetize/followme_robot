package it.unicam.cs.followme.list.model;
import com.sun.tools.jconsole.JConsoleContext;
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
        assertEquals(1.0, cartesianCoordinates.getX());
    }

    @Test
    public void shouldSetXValue() {
        CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
        cartesianCoordinates.setX(2.0);
        assertEquals(2.0, cartesianCoordinates.getX());
    }

    @Test
    public void testGetY() {
        CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
        assertEquals(1, cartesianCoordinates.getY());
    }

    @Test
    public void shouldSetYValue() {
        CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
        cartesianCoordinates.setY(2.0);
        assertEquals(2.0, cartesianCoordinates.getY());
    }

    @Test
    public void testCalculateDistance() {
        CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
        CartesianCoordinate cartesianCoordinates2 = new CartesianCoordinate(1.0, 1.0);
        assertEquals(0.0, cartesianCoordinates.calculateDistance(cartesianCoordinates2));
        CartesianCoordinate cartesianCoordinates3 = new CartesianCoordinate(7.0, 5.0);
        assertEquals(7.211102550927978, cartesianCoordinates.calculateDistance(cartesianCoordinates3));
        CartesianCoordinate cartesianCoordinates4 = new CartesianCoordinate(-4.0, 10);
        assertEquals(10.29563014098700, cartesianCoordinates.calculateDistance(cartesianCoordinates4));
    }
}
