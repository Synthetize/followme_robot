package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoneFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        LoopCommand startingLoopCommand = new Repeat(1,0,0);
        Done original = new Done(startingLoopCommand);
        DoneFactory factory = new DoneFactory(List.of(new Repeat(1,0,0)));
        Done cloned = (Done) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertNotEquals(original.startingLoopCommand(), cloned.startingLoopCommand());
        assertEquals(original.startingLoopCommand().getStartingLoopIndex(), cloned.startingLoopCommand().getStartingLoopIndex());
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        DoneFactory factory = new DoneFactory(null);
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
