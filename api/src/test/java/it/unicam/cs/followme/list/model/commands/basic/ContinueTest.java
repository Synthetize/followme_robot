package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContinueTest {
    Environment<BasicRobot> environment;
    @BeforeEach
    void setUp() {
        HashMap<BasicRobot, Coordinate> robots = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationEnvironment<>(shapes, robots);
    }
    @Test
    void shouldMoveTowardsLastMovementDirection() {
        BasicRobot robot = new BasicRobot();
        robot.setLastMovementDirection(new CartesianCoordinate(3,2));
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(0,0)));
        Continue<BasicRobot> continueCommand = new Continue<>(3, environment);
        continueCommand.run(robot, 1);
        assertEquals(9, environment.getRobotCoordinate(robot).getX());
        assertEquals(6, environment.getRobotCoordinate(robot).getY());
    }

    @Test
    void shouldRemainInTheSamePositionIfLastMovementDirectionIsZero() {
        BasicRobot robot = new BasicRobot();
        robot.setLastMovementDirection(new CartesianCoordinate(0,0));
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(0,0)));
        Continue<BasicRobot> continueCommand = new Continue<>(3, environment);
        continueCommand.run(robot,1);
        assertEquals(0, environment.getRobotCoordinate(robot).getX());
        assertEquals(0, environment.getRobotCoordinate(robot).getY());
    }
}
