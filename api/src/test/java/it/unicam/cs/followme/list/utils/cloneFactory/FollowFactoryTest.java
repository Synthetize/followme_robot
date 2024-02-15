package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Follow;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FollowFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
        Environment environment = new SimulationEnvironment(null, null);
        Follow original = new Follow("label", new double[]{1, 2, 3}, environment);
        FollowFactory factory = new FollowFactory();
        Follow cloned = (Follow) factory.cloneCommand(original);
        assertNotEquals(original, cloned);
        assertEquals(original.getEnvironment(), cloned.getEnvironment());
        assertEquals(original.getLabel(), cloned.getLabel());
        assertArrayEquals(original.getArgs(), cloned.getArgs());
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        FollowFactory factory = new FollowFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
