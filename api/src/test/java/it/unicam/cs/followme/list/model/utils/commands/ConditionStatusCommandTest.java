package it.unicam.cs.followme.list.model.utils.commands;

import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionStatusCommandTest {
    ConditionStatusCommand conditionStatusCommand;

    @Test
    void shouldCreateSignalConditionStatusCommand() {
        conditionStatusCommand = new ConditionStatusCommand(RobotCommand.SIGNAL, "signal");
        assertEquals(conditionStatusCommand.getCommandType(), RobotCommand.SIGNAL);
        assertEquals(conditionStatusCommand.getLabel(), "signal");
    }

    @Test
    void shouldCreateUnsignalConditionStatusCommand() {
        conditionStatusCommand = new ConditionStatusCommand(RobotCommand.UNSIGNAL, "unsignal");
        assertEquals(conditionStatusCommand.getCommandType(), RobotCommand.UNSIGNAL);
        assertEquals(conditionStatusCommand.getLabel(), "unsignal");
    }
}
