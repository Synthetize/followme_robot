package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationArea;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FollowTest {

//    Environment<BasicRobot> environment;
//
//    @BeforeEach
//    void setUp() {
//        HashMap<Shape, Coordinate> shapesDetails = new HashMap<>();
//        HashMap<BasicRobot, Coordinate> robotsDetails = new HashMap<>();
//        environment = new SimulationArea<>(shapesDetails, robotsDetails);
//    }
//
//    @Test
//    void shouldMoveRobotToTheAveragePosition() {
////        BasicRobot robot = new BasicRobot();
////        BasicRobot robot2 = new BasicRobot();
////        BasicRobot robot3 = new BasicRobot();
////        BasicRobot robot4 = new BasicRobot();
////        robot.addLabel("label_");
////        robot2.addLabel("label_");
////        robot3.addLabel("label_");
////        CartesianCoordinate robotCoordinate1 = new CartesianCoordinate(6, 1);
////        CartesianCoordinate robotCoordinate2 = new CartesianCoordinate(1, 6);
////        CartesianCoordinate robotCoordinate3 = new CartesianCoordinate(16, 16);
////        List<Coordinate> coordinates = List.of(robotCoordinate1, robotCoordinate2, robotCoordinate3,robotCoordinate1);
////        List<BasicRobot> robots = List.of(robot,robot2,robot3,robot4);
////        environment.addRobots(robots , coordinates);
////        BasicRobot robotThatShouldFollow = new BasicRobot();
////        CartesianCoordinate robotThatShouldFollowCoordinate = new CartesianCoordinate(1, 1);
////        environment.addRobots(List.of(robotThatShouldFollow), List.of(robotThatShouldFollowCoordinate));
////        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{5, 3}, environment);
////        followCommand.run(robotThatShouldFollow);
////        assertEquals(3.5, environment.getRobotCoordinate(robotThatShouldFollow).getX());
////        assertEquals(3.5, environment.getRobotCoordinate(robotThatShouldFollow).getY());
//    }
//
//    @Test
//    void shouldMoveRandomIfNoRobotsAreSignaling() {
//        BasicRobot robot = new BasicRobot();
//        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
//        environment.addRobots(List.of(robot), List.of(robotCoordinate));
//        BasicRobot robotThatShouldFollow = new BasicRobot();
//        CartesianCoordinate robotThatShouldFollowCoordinate = new CartesianCoordinate(5, 5);
//        environment.addRobots(List.of(robotThatShouldFollow), List.of(robotThatShouldFollowCoordinate));
//        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{1, 3}, environment);
//        followCommand.run(robotThatShouldFollow);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getX() >= -1 && environment.getRobotCoordinate(robotThatShouldFollow).getX() <= 1);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getY() >= -1 && environment.getRobotCoordinate(robotThatShouldFollow).getY() <= 1);
//    }
//
//    @Test
//    void shouldMoveRandomIfNoRobotsAreInDistance() {
//        BasicRobot robot = new BasicRobot();
//        robot.addLabel("label_");
//        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(10, 10)));
//        BasicRobot robotThatShouldFollow = new BasicRobot();
//        environment.addRobots(List.of(robotThatShouldFollow), List.of(new CartesianCoordinate(5, 5)));
//        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{4, 3}, environment);
//        followCommand.run(robotThatShouldFollow);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getX() >= -4 && environment.getRobotCoordinate(robotThatShouldFollow).getX() <= 4);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getY() >= -4 && environment.getRobotCoordinate(robotThatShouldFollow).getY() <= 4);
//    }
//
//    @Test
//    void shouldMoveRandomIfTheOnlyRobotSignalingIsTheRobotItself() {
//        BasicRobot robotThatShouldFollow = new BasicRobot();
//        robotThatShouldFollow.addLabel("label_");
//        environment.addRobots(List.of(robotThatShouldFollow), List.of(new CartesianCoordinate(5, 5)));
//        Follow<BasicRobot> followCommand = new Follow<>("label_", new double[]{2, 3}, environment);
//        followCommand.run(robotThatShouldFollow);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getX() >= -2 && environment.getRobotCoordinate(robotThatShouldFollow).getX() <= 2);
//        assertTrue(environment.getRobotCoordinate(robotThatShouldFollow).getY() >= -2 && environment.getRobotCoordinate(robotThatShouldFollow).getY() <= 2);
//    }
}
