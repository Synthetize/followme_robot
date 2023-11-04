package it.unicam.cs.followme.list.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationAreaTest {
    Environment<CartesianCoordinate> environment;

    @BeforeEach
    void init() {
        System.out.println("Before each");
        environment = new SimulationArea();
    }

    @Test
    void shouldAddShapes() {
        assertEquals(0, environment.getShapesDetails().size());
        CircleShape circleShape = new CircleShape(1, "LABEL_");
        CartesianCoordinate circleCoordinate = new CartesianCoordinate(1, 1);
        RectangleShape rectangleShape = new RectangleShape(1, 1, "LABEL_");
        CartesianCoordinate rectangleCoordinate = new CartesianCoordinate(2, 2);
        List<Shape> shapes = Arrays.asList(circleShape, rectangleShape);
        List<CartesianCoordinate> coordinates = Arrays.asList(circleCoordinate, rectangleCoordinate);
        environment.addShapes(shapes, coordinates);
        assertEquals(environment.getShapesDetails().size(), 2);
        assertEquals(environment.getShapesDetails().get(circleShape), circleCoordinate);
        assertEquals(environment.getShapesDetails().get(rectangleShape), rectangleCoordinate);
    }

    void shouldAddRobots() {
        assertEquals(0, environment.getRobotsDetails().size());
        Robot robot = new BaseRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(1, 1);
        List<Robot> robots = Arrays.asList(robot);
        List<CartesianCoordinate> coordinates = Arrays.asList(robotCoordinate);
        environment.addRobots(robots, coordinates);
        assertEquals(environment.getRobotsDetails().size(), 1);
        assertEquals(environment.getRobotsDetails().get(robot), robotCoordinate);
    }

    void shouldReturnTheRobotInsideAShape() {
    }

    @Test
    void shouldReturnTheDistanceBetweenTwoCoordinates() {
       CartesianCoordinate cartesianCoordinates = new CartesianCoordinate(1.0, 1.0);
       CartesianCoordinate cartesianCoordinates2 = new CartesianCoordinate(1.0, 1.0);
       assertEquals(0.0, environment.getDistanceBetweenTwoCoordinates(cartesianCoordinates, cartesianCoordinates2));
       CartesianCoordinate cartesianCoordinates3 = new CartesianCoordinate(7.0, 5.0);
       assertEquals(7.211102550927978, environment.getDistanceBetweenTwoCoordinates(cartesianCoordinates, cartesianCoordinates3));
       CartesianCoordinate cartesianCoordinates4 = new CartesianCoordinate(-4.0, 10);
       assertEquals(10.29563014098700, environment.getDistanceBetweenTwoCoordinates(cartesianCoordinates, cartesianCoordinates4));
    }
}
