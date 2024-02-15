package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Follow;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.basic.Stop;
import it.unicam.cs.followme.list.utils.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StopFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Stop original = new Stop();
        StopFactory factory = new StopFactory();
        Stop cloned = (Stop) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        StopFactory factory = new StopFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
