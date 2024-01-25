package it.unicam.cs.followme.list.model.commands;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

/**
 * This interface represents a single command of a program.
 */
public interface Command {
    /**
     * @return the command type
     */
    RobotCommand getCommandType();
}

