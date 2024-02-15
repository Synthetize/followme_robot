package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.basic.Signal;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SignalFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Environment environment = new SimulationEnvironment(null, null);
        Signal original = new Signal("label", environment);
        SignalFactory factory = new SignalFactory();
        Signal cloned = (Signal) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getEnvironment(), cloned.getEnvironment());
        assertEquals(original.getLabel(), cloned.getLabel());
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        SignalFactory factory = new SignalFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
