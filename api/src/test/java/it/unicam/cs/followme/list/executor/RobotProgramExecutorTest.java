package it.unicam.cs.followme.list.executor;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationArea;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
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

public class RobotProgramExecutorTest {
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
    void shouldRunTheEntireProgram() {
        BasicRobot robot = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        environment.addRobots(List.of(robot), List.of(robotCoordinate));
        CircleShape circleShape = new CircleShape(5, "label_");
        CartesianCoordinate circleCoordinate = new CartesianCoordinate(0, 0);
        environment.addShapes(List.of(circleShape), List.of(circleCoordinate));
        programParserHandler.signalCommand("label_");
        programParserHandler.untilCommandStart("label_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{-1, -1, 1});
        programParserHandler.moveCommand(new double[]{-1, -1, 1});
        programParserHandler.unsignalCommand("label_");
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        programExecutor.executeProgram(robot, 1, 1000);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("2,12", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("2,12", formatted);
    }

    //TODO: test with loop all loop types, and loop inside loop
    @Test
    void shouldStopExecutionIfTimeIsOver() {
        BasicRobot robot = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        environment.addRobots(List.of(robot), List.of(robotCoordinate));
        programParserHandler.repeatCommandStart(-1);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        programExecutor.executeProgram(robot, 0.5, 7);
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(environment.getRobotCoordinate(robot).getX());
        assertEquals("1,41", formatted);
        formatted = df.format(environment.getRobotCoordinate(robot).getY());
        assertEquals("1,41", formatted);
    }
}
