package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.utils.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RepeatFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Repeat original = new Repeat(1, 0, 0);
        RepeatFactory factory = new RepeatFactory();
        Repeat cloned = (Repeat) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getRepetitions(), cloned.getRepetitions());
        assertEquals(original.getStartingLoopIndex(), cloned.getStartingLoopIndex());
        assertEquals(original.getEndingLoopIndex(), cloned.getEndingLoopIndex());
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        RepeatFactory factory = new RepeatFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
