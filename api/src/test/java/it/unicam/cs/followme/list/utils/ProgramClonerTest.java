package it.unicam.cs.followme.list.utils;

import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramClonerTest {
    @Test
    void shouldCloneBasicCommand() {
        ProgramCloner programCloner = new ProgramCloner(new HashMap<>());
        List<Command> program = new ArrayList<>();
        program.add(new Move(new CartesianCoordinate(1,1), 3, null));
        program.add(new Continue(1, null));
        program.add(new Follow("label", new double[]{4,5}, null));
        program.add(new Stop());
        program.add(new Signal("label", null));
        program.add(new Unsignal("label", null));
        List<Command> cloned = programCloner.clone(program);
        assert(cloned.get(0) instanceof Move);
        assertNotEquals(cloned.get(0), program.get(0));
        assert(cloned.get(1) instanceof Continue);
        assertNotEquals(cloned.get(1), program.get(1));
        assert(cloned.get(2) instanceof Follow);
        assertNotEquals(cloned.get(2), program.get(2));
        assert(cloned.get(3) instanceof Stop);
        assertNotEquals(cloned.get(3), program.get(3));
        assert(cloned.get(4) instanceof Signal);
        assertEquals(cloned.get(4).getCommandType(), RobotCommand.SIGNAL);
        assertNotEquals(cloned.get(4), program.get(4));
        assert(cloned.get(5) instanceof Unsignal);
        assertEquals(cloned.get(5).getCommandType(), RobotCommand.UNSIGNAL);
        assertNotEquals(cloned.get(5), program.get(5));
    }

    @Test
    void shouldCloneLoopCommand() {
        ProgramCloner programCloner = new ProgramCloner(new HashMap<>());
        List<Command> program = new ArrayList<>();
        program.add(new Repeat(3, 0,1));
        program.add(new Done((LoopCommand) program.get(0)));
        program.add(new Until("label", 2, 3, null));
        program.add(new Done((LoopCommand) program.get(2)));
        List<Command> cloned = programCloner.clone(program);
        assert(cloned.get(0) instanceof Repeat);
        assertNotEquals(cloned.get(0), program.get(0));
        assert(cloned.get(1) instanceof Done);
        assertNotEquals(cloned.get(1), program.get(1));
        assertNotEquals(((Done) cloned.get(1)).startingLoopCommand(), program.get(0));
        assert(cloned.get(2) instanceof Until);
        assertNotEquals(cloned.get(1), program.get(1));
        assert(cloned.get(3) instanceof Done);
        assertNotEquals(cloned.get(3), program.get(3));
    }
}
