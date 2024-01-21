package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

/**
 * This class is extended by all the commands that need to perform some action
 * on the robot position.
 */
public abstract class RunnableCommand implements Command {
    /**
     * perform the command logic.
     *
     * @param robot   the robot on which the command is performed
     * @param delta_t the time elapsed since the last call
     */
    abstract public void run(Robot robot, double delta_t);
}
