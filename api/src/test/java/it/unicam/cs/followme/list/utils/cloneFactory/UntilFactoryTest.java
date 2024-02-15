package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Follow;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.utils.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UntilFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Environment environment = new SimulationEnvironment(null, null);
        Until original = new Until("label", 1, 2, environment);
        UntilFactory factory = new UntilFactory();
        Until cloned = (Until) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getEnvironment(), cloned.getEnvironment());
        assertEquals(original.getLabel(), cloned.getLabel());
        assertEquals(original.getStartingLoopIndex(), cloned.getStartingLoopIndex());
        assertEquals(original.getEndingLoopIndex(), cloned.getEndingLoopIndex());
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        UntilFactory factory = new UntilFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
