package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Done implements Command {
    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.DONE;
    }

    @Override
    public void run(Robot robot) {

    }
}
