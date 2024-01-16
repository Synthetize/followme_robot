package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneTest {
    Done done;

    @Test
    void shouldReturnTheReferenceToTheStartingLoopCommand() {
        Repeat repeat = new Repeat(1, 0, 0);
        done = new Done(repeat);
        assertEquals(done.startingLoopCommand(), repeat);
        Until until = new Until("label_", 0, 0, null);
        done = new Done(until);
        assertEquals(done.startingLoopCommand(), until);
    }

}
