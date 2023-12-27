package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.executor.ProgramExecutor;
import it.unicam.cs.followme.list.executor.RobotProgramExecutor;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationArea;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.list.parser_handler.ProgramParserHandler;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatTest {
    Environment<BasicRobot> environment;
    ProgramExecutor<BasicRobot> programExecutor;
    List<Command<BasicRobot>> program;
    FollowMeParserHandler programParserHandler;

    @BeforeEach
    void setUp() {
        HashMap<BasicRobot, Coordinate> robots = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationArea<>(shapes, robots);
        program = new ArrayList<>();
        programExecutor = new RobotProgramExecutor<>(program);
        programParserHandler = new ProgramParserHandler<>(environment, programExecutor);
        programParserHandler.parsingStarted();
    }

    @Test
    void shouldRunRepeatCommandWithNoOtherLoopInside() {
        BasicRobot robot = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        environment.addRobots(List.of(robot), List.of(robotCoordinate));
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        program.get(1).run(robot, 1);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("2,83", formatted);
    }

    @Test
    void shouldRunRepeatCommandWithTheSameTypeOfLoopInside() {
        BasicRobot robot = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        environment.addRobots(List.of(robot), List.of(robotCoordinate));
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        program.get(1).run(robot, 1);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("8,49", formatted);
    }

    @Test
    //TODO: fix this test
    void shouldRunRepeatCommandWithDifferentTypeOfLoopInside() {
//        BasicRobot robot = new BasicRobot();
//        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
//        environment.addRobots(List.of(robot), List.of(robotCoordinate));
//        programParserHandler.moveCommand(new double[]{1, 1, 1});
//        programParserHandler.repeatCommandStart(2);
//        programParserHandler.moveCommand(new double[]{1, 1, 1});
//        programParserHandler.moveCommand(new double[]{1, 1, 1});
//        programParserHandler.foreverCommandStart();
//        programParserHandler.moveCommand(new double[]{1, 1, 1});
//        programParserHandler.moveCommand(new double[]{1, 1, 1});
//        programParserHandler.doneCommand();
//        programParserHandler.doneCommand();
//        programParserHandler.parsingDone();
//        program.get(1).run(robot, 1);
//        DecimalFormat df = new DecimalFormat("#.##");
//        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
//        assertEquals("8,49", formatted);
    }
}
