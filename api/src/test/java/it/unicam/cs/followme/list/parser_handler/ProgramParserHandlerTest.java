package it.unicam.cs.followme.list.parser_handler;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.simulator.Simulator;
import it.unicam.cs.followme.list.simulator.RobotSimulator;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramParserHandlerTest {
    Environment environment;
    Simulator simulator;
    List<Command> program;
    FollowMeParserHandler programParserHandler;
    Map<Robot, Coordinate> robotsList;

    @BeforeEach
    void setUp() {
        environment = new SimulationEnvironment(null, null);
        program = new ArrayList<>();
        robotsList = new HashMap<>();
        simulator = new RobotSimulator(program, robotsList);
        programParserHandler = new ProgramParserHandler(environment, simulator);
        programParserHandler.parsingStarted();

    }

    @Test
    void shouldParseMoveCommand() {
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.moveCommand(new double[]{-1, -1, 3});
        programParserHandler.moveCommand(new double[]{0.5, -0.67, 1});
        programParserHandler.moveCommand(new double[]{0, 1, 5});
        programParserHandler.moveCommand(new double[]{1, 0, 2});
        programParserHandler.parsingDone();
        assertEquals(5, program.size());
    }

    @Test
    void shouldNotParseMoveCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveCommand(new double[]{1, -2, 4}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveCommand(new double[]{-2, 1, 1}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveCommand(new double[]{1, 1, -1}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveCommand(new double[]{0, 0, 4}));
        programParserHandler.parsingDone();
    }

    @Test
    void shouldParseMoveRandomCommand() {
        programParserHandler.moveRandomCommand(new double[]{1, 1, 2, 2, 1});
        programParserHandler.moveRandomCommand(new double[]{-1, -1, -2, -2, 1});
        programParserHandler.moveRandomCommand(new double[]{0.5, -0.67, 0.5, -0.67, 1});
        programParserHandler.moveRandomCommand(new double[]{0, 1, 0, 1, 1});
        programParserHandler.moveRandomCommand(new double[]{0, 0, 1, 0.5, 1});
        programParserHandler.parsingDone();
        assertEquals(5, program.size());
    }

    @Test
    void shouldNotParseMoveRandomCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveRandomCommand(new double[]{0, 0, 0, 0, 7}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveRandomCommand(new double[]{1, 1, 2, 2, -1}));
        programParserHandler.parsingDone();
    }

    @Test
    void shouldParseSignalCommand() {
        programParserHandler.signalCommand("label_");
        programParserHandler.parsingDone();
        assertEquals(1, program.size());
        assertInstanceOf(UpdateRobotLabel.class, program.get(0));
    }

    @Test
    void shouldNotParseSignalCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.signalCommand(""));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.signalCommand("nonValidLabel"));
        programParserHandler.parsingDone();
        assertEquals(0, program.size());
    }

    @Test
    void shouldParseUnsignalCommand() {
        programParserHandler.unsignalCommand("label_");
        programParserHandler.parsingDone();
        assertEquals(1, program.size());
        assertInstanceOf(UpdateRobotLabel.class, program.get(0));
    }

    @Test
    void shouldNotParseUnsignalCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.unsignalCommand(""));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.unsignalCommand("nonValidLabel"));
        programParserHandler.parsingDone();
        assertEquals(0, program.size());
    }

    @Test
    void shouldParseFollowCommand() {
        programParserHandler.followCommand("label_", new double[]{1, 1});
        programParserHandler.parsingDone();
        assertEquals(1, program.size());
    }

    @Test
    void shouldNotParseFollowCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.followCommand("", new double[]{1, 1}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.followCommand("nonValidLabel", new double[]{1, 1}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.followCommand("label_", new double[]{0, 1}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.followCommand("label_", new double[]{1, -1}));
        programParserHandler.parsingDone();
        assertEquals(0, program.size());
    }

    @Test
    void shouldParseStopCommand() {
        programParserHandler.stopCommand();
        programParserHandler.parsingDone();
        assertEquals(1, program.size());
        assertInstanceOf(Stop.class, program.get(0));
    }

    @Test
    void shouldParseContinueCommand() {
        programParserHandler.continueCommand(1);
        programParserHandler.parsingDone();
        assertEquals(1, program.size());
        assertInstanceOf(Continue.class, program.get(0));
    }

    @Test
    void shouldNotParseContinueCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.continueCommand(-1));
        programParserHandler.parsingDone();
        assertEquals(0, program.size());
    }

    @Test
    void shouldParseRepeatCommand() {
        programParserHandler.repeatCommandStart(3);
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.moveRandomCommand(new double[]{1, 1, 2, 2, 1});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        assertEquals(4, program.size());
        assertInstanceOf(Repeat.class, program.get(0));
        Repeat repeat = (Repeat) program.get(0);
        assertEquals(repeat.getStartingLoopIndex(), 0);
        assertEquals(repeat.getEndingLoopIndex(), 3);

        program.clear();
        programParserHandler.parsingStarted();
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.repeatCommandStart(3);
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();

        assertEquals(3, program.size());
        assertInstanceOf(Repeat.class, program.get(1));
        repeat = (Repeat) program.get(1);
        assertEquals(repeat.getStartingLoopIndex(), 1);
        assertEquals(repeat.getEndingLoopIndex(), 2);

        program.clear();
        programParserHandler.parsingStarted();
        programParserHandler.repeatCommandStart(4);
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.doneCommand();
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        assertEquals(7, program.size());
        assertInstanceOf(Repeat.class, program.get(0));
        repeat = (Repeat) program.get(0);
        assertEquals(repeat.getStartingLoopIndex(), 0);
        assertEquals(repeat.getEndingLoopIndex(), 6);
        assertInstanceOf(Repeat.class, program.get(2));
        repeat = (Repeat) program.get(2);
        assertEquals(repeat.getStartingLoopIndex(), 2);
        assertEquals(repeat.getEndingLoopIndex(), 4);
    }

    @Test
    void shouldNotParseRepeatCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.repeatCommandStart(0));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.repeatCommandStart(-2));
        programParserHandler.parsingDone();
        assertEquals(0, program.size());
    }

    @Test
    void shouldParseDoForeverCommand() {
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.doForeverStart();
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.repeatCommandStart(2);
        programParserHandler.doneCommand();
        programParserHandler.signalCommand("label_");
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        assertEquals(7, program.size());
        assertInstanceOf(Repeat.class, program.get(1));
        Repeat repeat = (Repeat) program.get(1);
        assertEquals(repeat.getStartingLoopIndex(), 1);
        assertEquals(repeat.getEndingLoopIndex(), 6);

        repeat = (Repeat) program.get(3);
        assertEquals(repeat.getStartingLoopIndex(), 3);
        assertEquals(repeat.getEndingLoopIndex(), 4);
    }

    @Test
    void shouldParseDoneCommand() {
        programParserHandler.untilCommandStart("label_");
        programParserHandler.repeatCommandStart(2);
        programParserHandler.doneCommand();
        programParserHandler.doneCommand();
        programParserHandler.parsingDone();
        assertEquals(4, program.size());
        assertInstanceOf(Done.class, program.get(2));
        assertInstanceOf(Done.class, program.get(3));

    }

}
