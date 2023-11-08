package it.unicam.cs.followme.list.model.utils.commands;

import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FollowCommandTest {
    FollowCommand followCommand;

    @Test
    public void shouldCreateFollowCommand() {
        followCommand = new FollowCommand("label", new double[]{3, 5});
        assertEquals(followCommand.getCommandType(), RobotCommand.FOLLOW);
        assertEquals(followCommand.getLabel(), "label");
        assertEquals(followCommand.getDistance(), 3);
        assertEquals(followCommand.getSpeed(), 5);
    }
}
