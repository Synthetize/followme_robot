package it.unicam.cs.followme.list.model.utils.commands;

import com.sun.tools.jconsole.JConsoleContext;
import com.sun.tools.jconsole.JConsolePlugin;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.utilities.RobotCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveCommandTest {

    MoveCommand moveCommand;
    @Test
    void shouldCreateBasicMoveCommand() {
        moveCommand = new MoveCommand(new CartesianCoordinate(1, 1), 1);
        assertEquals(moveCommand.getCommandType(), RobotCommand.MOVE);
        assertEquals(moveCommand.getTargetCoordinate(), new CartesianCoordinate(1, 1));
        assertEquals(1, moveCommand.getSpeed());
    }

}
