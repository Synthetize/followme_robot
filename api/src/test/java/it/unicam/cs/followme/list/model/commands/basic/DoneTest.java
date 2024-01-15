package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoneTest {
    Done<BasicRobot> done;

    @Test
    void shouldReturnTheReferenceToTheStartingLoopCommand() {
        Repeat<BasicRobot> repeat = new Repeat<>(1, 0, 0);
        done = new Done<>(repeat);
        assertTrue(done.startingLoopCommand.equals(repeat));
        Until<BasicRobot> until = new Until<>("label_", 0, 0, null);
        done = new Done<>(until);
        assertTrue(done.startingLoopCommand.equals(until));
    }

}
