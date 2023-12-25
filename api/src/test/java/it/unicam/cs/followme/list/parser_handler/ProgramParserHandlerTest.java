package it.unicam.cs.followme.list.parser_handler;

import it.unicam.cs.followme.list.executor.ProgramExecutor;
import it.unicam.cs.followme.list.executor.RobotProgramExecutor;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationArea;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramParserHandlerTest {
    Environment<BasicRobot> environment;
    ProgramExecutor<BasicRobot> programExecutor;
    List<Command<BasicRobot>> program;
    FollowMeParserHandler programParserHandler;
    @BeforeEach
    void setUp() {
        environment = new SimulationArea<>(null, null);
        program = new ArrayList<>();
        programExecutor = new RobotProgramExecutor<>(program);
        programParserHandler = new ProgramParserHandler<>(environment, programExecutor);
        programParserHandler.parsingStarted();

    }

    @Test
    void shouldParseMoveCommand() {
        programParserHandler.moveCommand(new double[]{1, 1, 4});
        programParserHandler.moveCommand(new double[]{-1, -1, 3});
        programParserHandler.moveCommand(new double[]{0.5,-0.67, 1});
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

//        IllegalArgumentException thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> programParserHandler.moveCommand(new double[]{-2, 1, 4})
//        );
//        assertEquals("Coordinate must be between -1 and 1", thrown.getMessage());
//        thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> programParserHandler.moveCommand(new double[]{1, -2, 4})
//        );
//        assertEquals("Coordinate must be between -1 and 1", thrown.getMessage());
//        IllegalArgumentException thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> programParserHandler.moveCommand(new double[]{1, 1, -1})
//        );
//        assertEquals("Speed must be greater than 0", thrown.getMessage());
//        thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> programParserHandler.moveCommand(new double[]{0, 0, 4})
//        );
//        assertEquals("At least one coordinate must be different from 0", thrown.getMessage());
        programParserHandler.parsingDone();
    }

    @Test
    void shouldParseMoveRandomCommand() {
        programParserHandler.moveRandomCommand(new double[]{1, 1, 2, 2, 1});
        programParserHandler.moveRandomCommand(new double[]{-1, -1, -2, -2, 1});
        programParserHandler.moveRandomCommand(new double[]{0.5,-0.67, 0.5, -0.67, 1});
        programParserHandler.moveRandomCommand(new double[]{0, 1, 0, 1, 1});
        programParserHandler.moveRandomCommand(new double[]{0, 0, 1, 0.5, 1});
        programParserHandler.parsingDone();
        assertEquals(5, program.size());
    }

    @Test
    void shouldNotParseMoveRandomCommand() {
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveRandomCommand(new double[]{0, 0, 0, 0, 7}));
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.moveRandomCommand(new double[]{1, 1, 2, 2, -1}));
//
//        IllegalArgumentException thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> programParserHandler.moveRandomCommand(new double[]{0, 0, 0, 0, 7})
//        );
//        assertEquals("The mean values of the coordinates cannot both be 0", thrown.getMessage());
//        thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> programParserHandler.moveRandomCommand(new double[]{1, 1, 2, 2, -1})
//        );
//        assertEquals("Speed must be greater than 0", thrown.getMessage());
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
        assertTrue(program.get(0) instanceof Stop);
    }

   void shouldParseContinueCommand() {
        programParserHandler.continueCommand(1);
        programParserHandler.parsingDone();
        assertEquals(1, program.size());
        assertInstanceOf(Continue.class, program.get(0));
    }

    @Test
    void shouldNotParseContinueCommand() {
        //assertThrows(IllegalArgumentException.class, () -> programParserHandler.continueCommand(0)); //TODO: speed can be 0?
        assertThrows(IllegalArgumentException.class, () -> programParserHandler.continueCommand(-1));
        programParserHandler.parsingDone();
        assertEquals(0, program.size());
    }
}
