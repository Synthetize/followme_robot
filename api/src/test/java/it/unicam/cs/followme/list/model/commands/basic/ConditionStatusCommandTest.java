package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.basic.ChangeRobotConditionStatus;
import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionStatusCommandTest {
    ChangeRobotConditionStatus conditionStatusCommand;

    @Test
    void shouldCreateSignalConditionStatusCommand() {
        conditionStatusCommand = new ChangeRobotConditionStatus(RobotCommand.SIGNAL, "signal");
        assertEquals(conditionStatusCommand.getCommandType(), RobotCommand.SIGNAL);
        assertEquals(conditionStatusCommand.getLabel(), "signal");
    }

    @Test
    void shouldCreateUnsignalConditionStatusCommand() {
        conditionStatusCommand = new ChangeRobotConditionStatus(RobotCommand.UNSIGNAL, "unsignal");
        assertEquals(conditionStatusCommand.getCommandType(), RobotCommand.UNSIGNAL);
        assertEquals(conditionStatusCommand.getLabel(), "unsignal");
    }
}
