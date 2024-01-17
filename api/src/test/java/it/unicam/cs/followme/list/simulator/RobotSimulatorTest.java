package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.parserHandler.ProgramParserHandler;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobotSimulatorTest {
    Environment environment;
    RobotSimulator simulator;
    List<Command> program;
    FollowMeParserHandler programParserHandler;
    HashMap<Robot, Coordinate> robotTestList;

    @BeforeEach
    void setUp() {
        robotTestList = new HashMap<>();
        HashMap<Shape, Coordinate> shapes = new HashMap<>();
        environment = new SimulationEnvironment(shapes, robotTestList);
        program = new ArrayList<>();
        simulator = new RobotSimulator(program, robotTestList);
        programParserHandler = new ProgramParserHandler(environment, simulator);
        programParserHandler.parsingStarted();
    }

    @Test
    void shouldRunBasicCommand() {
        BasicRobot robot = new BasicRobot();
        BasicRobot robot2 = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        robotTestList.put(robot, robotCoordinate);
        robotTestList.put(robot2, robotCoordinate);
        assertEquals(2, environment.robotsDetails().size());
        assertEquals(2, simulator.robotsList.size());
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.signalCommand("label_");
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.stopCommand();
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.unsignalCommand("label_");
        programParserHandler.parsingDone();
        simulator.setup();
        for (int i = 0; i < program.size(); i++) {
            simulator.simulate(1, 1000);
        }
        assertEquals("2,12", String.format("%.2f", environment.getRobotCoordinate(robot).getX()));
        assertEquals("2,12", String.format("%.2f", environment.getRobotCoordinate(robot).getY()));
        assertEquals("2,12", String.format("%.2f", environment.getRobotCoordinate(robot2).getX()));
        assertEquals("2,12", String.format("%.2f", environment.getRobotCoordinate(robot2).getY()));
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
        simulator.setup();
        for (int i = 0; i < 10 ; i++) {
            simulator.simulate(1, 1000);
        }
        assertEquals("2,83", String.format("%.2f", environment.getRobotCoordinate(robot).getX()));
        assertEquals("2,83", String.format("%.2f", environment.getRobotCoordinate(robot).getY()));
        assertEquals("2,83", String.format("%.2f", environment.getRobotCoordinate(robot2).getX()));
        assertEquals("2,83", String.format("%.2f", environment.getRobotCoordinate(robot2).getY()));
    }

    @Test
    void shouldStopExecutionIfSimulationMaxTimeIsReached() {
        BasicRobot robot = new BasicRobot();
        BasicRobot robot2 = new BasicRobot();
        BasicRobot robot3 = new BasicRobot();
        CartesianCoordinate robotCoordinate = new CartesianCoordinate(0, 0);
        robotTestList.put(robot, robotCoordinate);
        robotTestList.put(robot2, robotCoordinate);
        robotTestList.put(robot3, new CartesianCoordinate(3, 7));
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 1});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        simulator.setup();
        for (int i = 0; i < program.size(); i++) {
            simulator.simulate(1, 3);
        }        assertEquals("0,71", String.format("%.2f", environment.getRobotCoordinate(robot).getX()));
        assertEquals("0,71", String.format("%.2f", environment.getRobotCoordinate(robot).getY()));
        assertEquals("0,71", String.format("%.2f", environment.getRobotCoordinate(robot2).getX()));
        assertEquals("0,71", String.format("%.2f", environment.getRobotCoordinate(robot2).getY()));
        assertEquals("3,71", String.format("%.2f", environment.getRobotCoordinate(robot3).getX()));
        assertEquals("7,71", String.format("%.2f", environment.getRobotCoordinate(robot3).getY()));

    }
}
