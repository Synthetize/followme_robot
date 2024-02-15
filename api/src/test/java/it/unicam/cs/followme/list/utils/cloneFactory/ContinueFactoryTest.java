package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Continue;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContinueFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Environment environment = new SimulationEnvironment(null, null);
        Continue original = new Continue(1, environment);
        ContinueFactory factory = new ContinueFactory();
        Continue cloned = (Continue) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getEnvironment(), cloned.getEnvironment());
        assertEquals(original.getSeconds(), cloned.getSeconds());
    }

    @Test
    void shouldNotClone() {
        Environment environment = new SimulationEnvironment(null, null);
        Move original = new Move(null, 1, environment);
        ContinueFactory factory = new ContinueFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
