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
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FollowTest {

    Environment environment;

    @BeforeEach
    void setUp() {
        HashMap<Shape, Coordinate> shapesDetails = new HashMap<>();
        HashMap<Robot, Coordinate> robotsDetails = new HashMap<>();
        environment = new SimulationEnvironment(shapesDetails, robotsDetails);
    }

    @Test
    void shouldMoveRobotToTheAveragePosition() {
        BasicRobot robot1 = new BasicRobot();
        BasicRobot robot2 = new BasicRobot();
        BasicRobot robot3 = new BasicRobot();
        BasicRobot robot4 = new BasicRobot();
        BasicRobot robotThatShouldFollow = new BasicRobot();
        robot1.addLabel("label_");
        robot2.addLabel("label_");
        robot3.addLabel("label_");
        CartesianCoordinate robotCoordinate1 = new CartesianCoordinate(6, 1);
        CartesianCoordinate robotCoordinate2 = new CartesianCoordinate(1, 6);
        CartesianCoordinate robotCoordinate3 = new CartesianCoordinate(16, 16);
        CartesianCoordinate robotThatShouldFollowCoordinate = new CartesianCoordinate(1, 1);
        List<Coordinate> coordinates = List.of(robotCoordinate1, robotCoordinate2, robotCoordinate3,robotCoordinate1, robotThatShouldFollowCoordinate);
        List<Robot> robots = List.of(robot1,robot2,robot3,robot4, robotThatShouldFollow);
        environment.addRobots(robots , coordinates);
        Follow followCommand = new Follow("label_", new double[]{5, 3}, environment);
        followCommand.run(robotThatShouldFollow, 1);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robotThatShouldFollow).getX());
        assertEquals("3,12", formatted);
        formatted = df.format(environment.getRobotCoordinate(robotThatShouldFollow).getY());
        assertEquals("3,12", formatted);
    }
//TODO: fix this tests

//    @Test
//    void shouldMoveRandomIfNoRobotsAreSignaling() {
//        BasicRobot robot = new BasicRobot();
//        BasicRobot robotThatShouldFollow = new BasicRobot();
//        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
//        CartesianCoordinate robotThatShouldFollowCoordinate = new CartesianCoordinate(3, 3);
//        environment.addRobots(List.of(robot, robotThatShouldFollow), List.of(robotCoordinate, robotThatShouldFollowCoordinate));
//        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{1, 3}, environment);
//        followCommand.run(robotThatShouldFollow, 1);
//    }
//
//    @Test
//    void shouldMoveRandomIfNoRobotsAreInDistance() {
//        BasicRobot robot = new BasicRobot();
//        BasicRobot robotThatShouldFollow = new BasicRobot();
//        robot.addLabel("label_");
//        environment.addRobots(List.of(robot, robotThatShouldFollow), List.of(new CartesianCoordinate(10, 10), new CartesianCoordinate(1, 1)));
//        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{4, 3}, environment);
//        followCommand.run(robotThatShouldFollow, 1);
//
//    }
//
//    @Test
//    void shouldMoveRandomIfTheOnlyRobotSignalingIsTheRobotItself() {
//        BasicRobot robotThatShouldFollow = new BasicRobot();
//        robotThatShouldFollow.addLabel("label_");
//        environment.addRobots(List.of(robotThatShouldFollow), List.of(new CartesianCoordinate(5, 5)));
//        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{2, 3}, environment);
//        followCommand.run(robotThatShouldFollow, 1);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getX() >= -2.121 && environment.getRobotCoordinate(robotThatShouldFollow).getX() <= 2.121);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getY() >= -2.121 && environment.getRobotCoordinate(robotThatShouldFollow).getY() <= 2);
//    }
}
