package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.basic.Unsignal;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnsignalFactoryTest {

    @Test
    void shouldClone() throws CloneCommandException {
        Environment environment = new SimulationEnvironment(null, null);
        Unsignal original = new Unsignal("label", environment);
        UnsignalFactory factory = new UnsignalFactory();
        Unsignal cloned = (Unsignal) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getEnvironment(), cloned.getEnvironment());
        assertEquals(original.getLabel(), cloned.getLabel());
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        UnsignalFactory factory = new UnsignalFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
