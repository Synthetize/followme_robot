package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateRobotLabelTest {
    UpdateRobotLabel<BasicRobot> command;
    Environment<BasicRobot> environment;

    @BeforeEach
    void init() {
        HashMap<Shape, Coordinate> shapesDetails = new HashMap<>();
        HashMap<BasicRobot, Coordinate> robotsDetails = new HashMap<>();
        environment = new SimulationEnvironment<>(shapesDetails, robotsDetails);
    }


    @Test
    void shouldCreateSignalConditionStatusCommand() {
        command = new UpdateRobotLabel<>("label_", environment, RobotCommand.SIGNAL);
        assertEquals(command.getCommandType(), RobotCommand.SIGNAL);
    }

    @Test
    void shouldCreateUnsignalConditionStatusCommand() {
        command = new UpdateRobotLabel<>("label_", environment, RobotCommand.UNSIGNAL);
        assertEquals(command.getCommandType(), RobotCommand.UNSIGNAL);
    }

    @Test
    void shouldAddLabelToRobot() {
        BasicRobot robot = new BasicRobot();
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        Shape rectangleShape = new RectangleShape(6, 6, "label0_");
        Shape circleShape = new CircleShape(6, "label1_");
        CartesianCoordinate coordinate = new CartesianCoordinate(0, 0);
        environment.addShapes(List.of(rectangleShape, circleShape), List.of(coordinate, coordinate));
        UpdateRobotLabel<BasicRobot> commandLabel0 = new UpdateRobotLabel<>("label0_", environment, RobotCommand.SIGNAL);
        commandLabel0.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 1);
        UpdateRobotLabel<BasicRobot> commandLabel1 = new UpdateRobotLabel<>("label1_", environment, RobotCommand.SIGNAL);
        commandLabel1.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 2);
    }

    @Test
    void shouldNotAddLabelToRobot() {
        BasicRobot robot = new BasicRobot();
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        Shape rectangleShape = new RectangleShape(6, 6, "label0_");
        Shape circleShape = new CircleShape(6, "label1_");
        CartesianCoordinate coordinate = new CartesianCoordinate(0, 0);
        environment.addShapes(List.of(rectangleShape, circleShape), List.of(coordinate, coordinate));
        command = new UpdateRobotLabel<>("wronglabel_", environment, RobotCommand.SIGNAL);
        command.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 0);
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
        command = new UpdateRobotLabel<>("label0_", environment, RobotCommand.UNSIGNAL);
        command.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 1);
        command = new UpdateRobotLabel<>("label1_", environment, RobotCommand.UNSIGNAL);
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
        command = new UpdateRobotLabel<>("wronglabel_", environment, RobotCommand.UNSIGNAL);
        command.run(robot, 1);
        assertEquals(robot.getCurrentConditionLabels().size(), 2);
    }
}
