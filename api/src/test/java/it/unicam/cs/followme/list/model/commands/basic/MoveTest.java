package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {

    Environment environment;

    @BeforeEach
    void setUp() {
        HashMap<Robot, Coordinate> robots = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationEnvironment(shapes, robots);
    }

    @Test
    void shouldMoveTheRobotInTheDirectionOfTargetPosition() {
        Move moveCommand = new Move(new CartesianCoordinate(1, 1), 5, environment);
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(9, 6)));
        moveCommand.run(robot, 1);
        DecimalFormat df = new DecimalFormat("#.##");

        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("12,54", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("9,54", formatted);
        formatted = df.format(robot.getLastMovementValues().getX());
        assertEquals("3,54", formatted);
        formatted = df.format(robot.getLastMovementValues().getY());
        assertEquals("3,54", formatted);

        Move moveCommand2 = new Move(new CartesianCoordinate(-1, 1), 6, environment);
        moveCommand2.run(robot, 0.5);
        formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("10,41", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("11,66", formatted);
        formatted = df.format(robot.getLastMovementValues().getX());
        assertEquals("-2,12", formatted);
        formatted = df.format(robot.getLastMovementValues().getY());
        assertEquals("2,12", formatted);
    }
}
