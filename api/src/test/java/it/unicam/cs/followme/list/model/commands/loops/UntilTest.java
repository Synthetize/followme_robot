package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.utils.SimulationTimer;
import it.unicam.cs.followme.list.simulator.Simulator;
import it.unicam.cs.followme.list.simulator.RobotSimulator;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationArea;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UntilTest {
    Environment<BasicRobot> environment;
    Simulator<BasicRobot> simulator;
    List<Command<BasicRobot>> program;
    FollowMeParserHandler programParserHandler;

    @BeforeEach
    void setUp() {
        HashMap<BasicRobot, Coordinate> robots = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationArea<>(shapes, robots);
        program = new ArrayList<>();
        simulator = new RobotSimulator<>(program);
        programParserHandler = new ProgramParserHandler<>(environment, simulator);
        programParserHandler.parsingStarted();
        SimulationTimer.setSimulationEndTime(1000);
    }

    @Test
    void shouldRunUntilCommandWithNoOtherLoopInside() {
        BasicRobot robot = new BasicRobot();
        robot.addLabel("label_");
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        CircleShape circleShape = new CircleShape(5, "label_");
        environment.addShapes(List.of(circleShape), List.of(new CartesianCoordinate(0, 0)));
        programParserHandler.untilCommandStart("label_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        program.get(0).run(robot, 1);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("4,24", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("4,24", formatted);
    }

    @Test
    void shouldRunUntilCommandWithTheSameTypeOfLoopInside() {
        BasicRobot robot = new BasicRobot();
        robot.addLabel("label_");
        robot.addLabel("label2_");
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        CircleShape circleShape = new CircleShape(5, "label_");
        CircleShape circleShape2 = new CircleShape(3, "label2_");
        environment.addShapes(List.of(circleShape, circleShape2), List.of(new CartesianCoordinate(0, 0), new CartesianCoordinate(0, 0)));
        programParserHandler.untilCommandStart("label2_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.untilCommandStart("label_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        program.get(0).run(robot, 1);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("4,24", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("4,24", formatted);
    }

    @Test
    void shouldRunUntilCommandWithDifferentTypeOfLoopInside() {
        BasicRobot robot = new BasicRobot();
        robot.addLabel("label_");
        environment.addRobots(List.of(robot), List.of(new CartesianCoordinate(0, 0)));
        CircleShape circleShape = new CircleShape(6, "label_");
        environment.addShapes(List.of(circleShape), List.of(new CartesianCoordinate(0, 0)));
        programParserHandler.untilCommandStart("label_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        program.get(0).run(robot, 1);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("6,36", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("6,36", formatted);
    }
}
