package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Done<R extends Robot> implements Command<R> {
    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.DONE;
    }

    @Override
    public void run(Robot robot, double delta_t) {

    }
}
