package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnsignalTest {
    Unsignal command;
    Environment environment;
    @BeforeEach
    void init() {
        HashMap<Shape, Coordinate> shapesDetails = new HashMap<>();
        HashMap<Robot, Coordinate> robotsDetails = new HashMap<>();
        environment = new SimulationEnvironment(shapesDetails, robotsDetails);
    }
    @Test
    void shouldRemoveLabelFromRobot() {
        BasicRobot robot = new BasicRobot();
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        Shape rectangleShape = new RectangleShape(6, 6, "label0_");
        Shape circleShape = new CircleShape(6, "label1_");
        CartesianCoordinate coordinate = new CartesianCoordinate(0, 0);
        environment.addShapes(List.of(rectangleShape, circleShape), List.of(coordinate, coordinate));
        robot.addLabel("label0_");
        robot.addLabel("label1_");
        command = new Unsignal("label0_", environment);
        command.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 1);
        command = new Unsignal("label1_", environment);
        command.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 0);
    }

    @Test
    void shouldNotRemoveLabelFromRobot() {
        BasicRobot robot = new BasicRobot();
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        Shape rectangleShape = new RectangleShape(6, 6, "label0_");
        Shape circleShape = new CircleShape(6, "label1_");
        CartesianCoordinate coordinate = new CartesianCoordinate(0, 0);
        environment.addShapes(List.of(rectangleShape, circleShape), List.of(coordinate, coordinate));
        robot.addLabel("label0_");
        robot.addLabel("label1_");
        command = new Unsignal("wronglabel_", environment);
        command.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 2);
    }
}
