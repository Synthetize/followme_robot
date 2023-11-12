package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationArea;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveTest {

    Environment<BasicRobot> environment;

    @BeforeEach
    void setUp() {
        HashMap<BasicRobot, Coordinate> robots = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationArea<>(shapes, robots);
    }
    @Test
    void shouldMoveTheRobotToTargetPosition() {
        Move<BasicRobot> moveCommand = new Move<>(new CartesianCoordinate(9, 6), 3, environment);
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(0,0)));
        moveCommand.run(robot);
        assertEquals(9, environment.getRobotCoordinate(robot).getX());
        assertEquals(6, environment.getRobotCoordinate(robot).getY());
        Move<BasicRobot> moveCommand2 = new Move<>(new CartesianCoordinate(-7, 14), 3, environment);
        moveCommand2.run(robot);
        assertEquals(-7, Math.round((environment.getRobotCoordinate(robot).getX()*100)/100));
        assertEquals(14, Math.round((environment.getRobotCoordinate(robot).getY()*100)/100));
    }

    @Test
    void shouldSetRobotLastDirectionAfterMovement() {
        Move<BasicRobot> moveCommand = new Move<>(new CartesianCoordinate(9, 6), 3, environment);
        BasicRobot robot = new BasicRobot();
        environment.addRobots(Collections.singletonList(robot), Collections.singletonList(new CartesianCoordinate(0,0)));
        moveCommand.run(robot);
        assertEquals(3, robot.getLastMovementDirection().getX());
        assertEquals(2, robot.getLastMovementDirection().getY());
    }

}
