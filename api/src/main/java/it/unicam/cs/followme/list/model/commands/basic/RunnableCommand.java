package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

/**
 * This interface is extended by all the commands that need to perform some action
 * on the robot position or on the robot state,
 */
public interface RunnableCommand extends Command {
    /**
     * perform the command logic.
     *
     * @param robot   the robot on which the command is performed
     * @param delta_t the time elapsed since the last call
     */
    void run(Robot robot, double delta_t);
}
