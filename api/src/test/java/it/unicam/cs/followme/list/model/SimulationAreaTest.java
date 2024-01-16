package it.unicam.cs.followme.list.model;

import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationAreaTest {
    SimulationEnvironment environment;
    RectangleShape rectangleShape;
    CartesianCoordinate rectangleCoordinate;
    CircleShape circleShape;
    CartesianCoordinate circleCoordinate;

    @BeforeEach
    void init() {
        environment = new SimulationEnvironment(new HashMap<>(), new HashMap<>());
        rectangleShape = new RectangleShape(6, 4, "LABEL_");
        rectangleCoordinate = new CartesianCoordinate(5, 3);
        circleShape = new CircleShape(5, "LABEL_");
        circleCoordinate = new CartesianCoordinate(0, 0);
    }

    @Test
    void shouldAddShapes() {
        assertEquals(0,
                environment.shapesDetails().size());
        CircleShape circleShape = new CircleShape(1, "LABEL_");
        CartesianCoordinate circleCoordinate = new CartesianCoordinate(1, 1);
        RectangleShape rectangleShape = new RectangleShape(1, 1, "LABEL_");
        CartesianCoordinate rectangleCoordinate = new CartesianCoordinate(2, 2);
        List<Shape> shapes = Arrays.asList(circleShape, rectangleShape);
        List<Coordinate> coordinates = Arrays.asList(circleCoordinate, rectangleCoordinate);
        environment.addShapes(shapes, coordinates);
        assertEquals(environment.shapesDetails().size(), 2);
        assertEquals(environment.shapesDetails().get(circleShape), circleCoordinate);
        assertEquals(environment.shapesDetails().get(rectangleShape), rectangleCoordinate);
    }

    @Test
    void shouldAddRobots() {
        assertEquals(0, environment.robotsDetails().size());
        BasicRobot robot = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(1, 1);
        BasicRobot robot2 = new BasicRobot();
        List<Robot> robots = List.of(robot, robot2);
        List<Coordinate> coordinates = List.of(robotCoordinate, robotCoordinate);
        environment.addRobots(robots, coordinates);
        assertEquals(environment.robotsDetails().size(), 2);
        assertEquals(environment.robotsDetails().get(robot), robotCoordinate);
    }

    @Test
    void robotShouldBeInsideRectangle() {
        environment.addShapes(Collections.singletonList(rectangleShape), Collections.singletonList(rectangleCoordinate));
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(4, 2)));
        assertEquals(1, environment.checkIfRobotIsInsideShapes(robot).size());

        BasicRobot robot2 = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot2), Collections.singletonList(new CartesianCoordinate(6, 4)));
        assertEquals(1, environment.checkIfRobotIsInsideShapes(robot2).size());

    }

    @Test
    void robotShouldBeOutsideRectangle() {
        environment.addShapes(Collections.singletonList(rectangleShape), Collections.singletonList(rectangleCoordinate));
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(10, 5)));
        assertEquals(0, environment.checkIfRobotIsInsideShapes(robot).size());
    }

    @Test
    void robotShouldBeInsideCircle() {
        environment.addShapes(Collections.singletonList(circleShape), Collections.singletonList(circleCoordinate));
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(3, 4)));
        assertEquals(1, environment.checkIfRobotIsInsideShapes(robot).size());
        BasicRobot robot2 = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot2), Collections.singletonList(new CartesianCoordinate(-2, -2)));
        assertEquals(1, environment.checkIfRobotIsInsideShapes(robot).size());
    }

    @Test
    void robotShouldBeOutsideCircle() {
        environment.addShapes(Collections.singletonList(circleShape), Collections.singletonList(circleCoordinate));
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(7, 0)));
        assertEquals(0, environment.checkIfRobotIsInsideShapes(robot).size());
        BasicRobot robot2 = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot2), Collections.singletonList(new CartesianCoordinate(0, 6)));
        assertEquals(0, environment.checkIfRobotIsInsideShapes(robot).size());
    }

    @Test
    void RObotShouldBeInsideRectangleAndCircle() {
        environment.addShapes(Collections.singletonList(circleShape), Collections.singletonList(circleCoordinate));
        environment.addShapes(Collections.singletonList(rectangleShape), Collections.singletonList(rectangleCoordinate));
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(3, 4)));
        assertEquals(2, environment.checkIfRobotIsInsideShapes(robot).size());
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

    @Test
    void shouldReturnRobotPosition() {
        BasicRobot robot = new BasicRobot();
        CartesianCoordinate coordinate = new CartesianCoordinate(1, 1);
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(coordinate));
        assertEquals(coordinate, environment.getRobotCoordinate(robot));
    }

    @Test
    void shouldReturnShapePosition() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1, 1);
        environment.addShapes(Collections.singletonList(rectangleShape), Collections.singletonList(coordinate));
        assertEquals(coordinate, environment.getShapeCoordinate(rectangleShape));
    }

    @Test
    void shouldSetRobotPosition() {
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(0, 0)));
        CartesianCoordinate coordinate = new CartesianCoordinate(5, 5);
        environment.setRobotPosition(robot, coordinate);
        assertEquals(coordinate, environment.getRobotCoordinate(robot));
        assertEquals(5, environment.robotsDetails().get(robot).getX());
        assertEquals(5, environment.robotsDetails().get(robot).getY());
    }

}
