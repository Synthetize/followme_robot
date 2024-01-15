package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.parser_handler.ProgramParserHandler;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotSimulatorTest {
    Environment<BasicRobot> environment;
    RobotSimulator<BasicRobot> simulator;
    List<Command<BasicRobot>> program;
    FollowMeParserHandler programParserHandler;
    Map<BasicRobot, Coordinate> robotsList;
    HashMap<BasicRobot, Coordinate> robotTestList;

    @BeforeEach
    void setUp() {
        robotTestList = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationEnvironment<>(shapes, robotTestList);
        program = new ArrayList<>();
        simulator = new RobotSimulator<>(program, robotTestList);
        programParserHandler = new ProgramParserHandler<>(environment, simulator);
        programParserHandler.parsingStarted();
    }

    @Test
    void shouldRunBasicCommand() {
        BasicRobot robot = new BasicRobot();
        BasicRobot robot2 = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        robotTestList.put(robot, robotCoordinate);
        robotTestList.put(robot2, robotCoordinate);
        assertEquals(2, environment.getRobotsDetails().size());
        assertEquals(2, simulator.robotsList.size());
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.signalCommand("label_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.stopCommand();
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.unsignalCommand("label_");
        programParserHandler.parsingDone();
        simulator.simulate(1, 1000);
        assertEquals("2,12",String.format("%.2f", environment.getRobotCoordinate(robot).getX()));
        assertEquals("2,12",String.format("%.2f", environment.getRobotCoordinate(robot).getY()));
        assertEquals("2,12",String.format("%.2f", environment.getRobotCoordinate(robot2).getX()));
        assertEquals("2,12",String.format("%.2f", environment.getRobotCoordinate(robot2).getY()));
    }

    @Test
    void shouldRunLoopCommand() {
        BasicRobot robot = new BasicRobot();
        BasicRobot robot2 = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        robotTestList.put(robot, robotCoordinate);
        robotTestList.put(robot2, robotCoordinate);
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        simulator.simulate(1, 1000);
        assertEquals("2,81",String.format("%.2f", environment.getRobotCoordinate(robot).getX()));
        assertEquals("2,81",String.format("%.2f", environment.getRobotCoordinate(robot).getY()));
    }
}
