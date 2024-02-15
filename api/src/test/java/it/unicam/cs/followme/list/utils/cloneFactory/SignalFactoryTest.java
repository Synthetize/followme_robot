package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.basic.Follow;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.model.commands.basic.UpdateRobotLabel;
import it.unicam.cs.followme.list.utils.CloneCommandException;
import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.Test;
import sun.misc.Signal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SignalFactoryTest {
    @Test
    void shouldClone() throws CloneCommandException {
//        Environment environment = new SimulationEnvironment(null, null);
//        UpdateRobotLabel original = new UpdateRobotLabel("label", environment, RobotCommand.SIGNAL);
//        SignalFactory factory = new SignalFactory();
//        UpdateRobotLabel cloned = (UpdateRobotLabel) factory.cloneCommand(original);
//        assertNotEquals(original, cloned);
//        assertEquals(original.getEnvironment(), cloned.getEnvironment());
//        assertEquals(original.getLabel(), cloned.getLabel());
//        assertArrayEquals(original.getArgs(), cloned.getArgs());
        assertEquals(true, false);
    }

    @Test
    void shouldNotClone() {
        Move original = new Move(null, 1, null);
        FollowFactory factory = new FollowFactory();
        assertThrows(CloneCommandException.class, () -> factory.cloneCommand(original));
    }
}
