package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Done implements Command {
    protected final LoopCommand startingLoopCommand;

    public Done(LoopCommand startingLoopCommand) {
        this.startingLoopCommand = startingLoopCommand;
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.DONE;
    }

    @Override
    public void run(Robot robot, double delta_t) {
    }

    public LoopCommand getStartingLoopCommand() {
        return startingLoopCommand;
    }
}
