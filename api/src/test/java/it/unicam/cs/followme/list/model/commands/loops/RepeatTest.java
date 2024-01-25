package it.unicam.cs.followme.list.model.commands.loops;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatTest {

    Repeat repeat;
    @Test
    void shouldReturnTrueIfRepeatIsForever() {
        repeat = new Repeat(-1, 0, 0);
        assertTrue(repeat.isLoopStillRunning(null));
    }

    @Test
    void shouldReturnTrueIfRepetitionNumbersIsGreaterThanZero() {
        repeat = new Repeat(2, 0, 0);
        assertTrue(repeat.isLoopStillRunning(null));
    }

    @Test
    void shouldReturnFalseIfRepetitionNumbersIsZero() {
        repeat = new Repeat(0, 0, 0);
        assertFalse(repeat.isLoopStillRunning(null));
    }

    @Test
    void shouldReturnFalseIfRepetitionNumberReachZero() {
        repeat = new Repeat(1, 0, 0);
        repeat.isLoopStillRunning(null);
        assertFalse(repeat.isLoopStillRunning(null));
    }

}
