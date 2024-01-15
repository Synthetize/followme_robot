package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.robots.BasicRobot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatTest {

    Repeat repeat;
    @Test
    void shouldReturnTrueIfRepeatIsForever() {
        repeat = new Repeat(-1, 0, 0);
        assertTrue(repeat.conditionStatus(null));
    }

    @Test
    void shouldReturnTrueIfRepetitionNumbersIsGreaterThanZero() {
        repeat = new Repeat(2, 0, 0);
        assertTrue(repeat.conditionStatus(null));
    }

    @Test
    void shouldReturnFalseIfRepetitionNumbersIsZero() {
        repeat = new Repeat(0, 0, 0);
        assertFalse(repeat.conditionStatus(null));
    }

    @Test
    void shouldReturnFalseIfRepetitionNumberReachZero() {
        repeat = new Repeat(1, 0, 0);
        repeat.conditionStatus(null);
        assertFalse(repeat.conditionStatus(null));
    }

}
