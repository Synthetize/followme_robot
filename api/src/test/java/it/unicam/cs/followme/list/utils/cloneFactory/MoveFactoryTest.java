package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Environment environment = new SimulationEnvironment(null, null);
        Move original = new Move(new CartesianCoordinate(0, 0), 1, environment);
        MoveFactory factory = new MoveFactory();
        Move cloned = (Move) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getEnvironment(), cloned.getEnvironment());
        assertEquals(original.getCoordinate(), cloned.getCoordinate());
        assertEquals(original.getSpeed(), cloned.getSpeed());
    }

    @Test
    void shouldNotClone() {
        Done original = new Done(null);
        MoveFactory factory = new MoveFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
