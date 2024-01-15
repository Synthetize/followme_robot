package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.utils.SimulationTimer;
import it.unicam.cs.followme.list.simulator.Simulator;
import it.unicam.cs.followme.list.simulator.RobotSimulator;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.parser_handler.ProgramParserHandler;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UntilTest {
    Until<BasicRobot> until;
    Map<Shape, Coordinate> shapesDetails;
    Map<BasicRobot, Coordinate> robotsDetails;

    @Test
    void shouldReturnTrueIfRobotInSignalingLabelAndIsInsideTheShape() {
        shapesDetails = new HashMap<>();
        robotsDetails = new HashMap<>();
        until = new Until<>("label_", 0, 0, new SimulationEnvironment<>(shapesDetails, robotsDetails));
        BasicRobot robot = new BasicRobot();
        robot.addLabel("label_");
        robotsDetails.put(robot, new CartesianCoordinate(0, 0));
        shapesDetails.put(new CircleShape(1, "label_"), new CartesianCoordinate(0, 0));
        assertTrue(until.conditionStatus(robot));
    }

    @Test
    void shouldReturnFalseIfRobotInSignalingLabelAndIsNotInsideTheShape() {
        shapesDetails = new HashMap<>();
        robotsDetails = new HashMap<>();
        until = new Until<>("label_", 0, 0, new SimulationEnvironment<>(shapesDetails, robotsDetails));
        BasicRobot robot = new BasicRobot();
        robot.addLabel("label_");
        robotsDetails.put(robot, new CartesianCoordinate(0, 0));
        shapesDetails.put(new CircleShape(1, "label_"), new CartesianCoordinate(1, 1));
        assertFalse(until.conditionStatus(robot));
    }
}
