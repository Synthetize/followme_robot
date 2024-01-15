package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Done<R extends Robot> implements Command<R> {
    protected final LoopCommand<R> startingLoopCommand;
    protected boolean loopEnded = false;

    public Done(LoopCommand<R> startingLoopCommand) {
        this.startingLoopCommand = startingLoopCommand;
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.DONE;
    }

    @Override
    public void run(R robot, double delta_t) {
        loopEnded = startingLoopCommand.conditionStatus(robot);
    }

    public boolean isLoopEnded() {
        return loopEnded;
    }

    public LoopCommand<R> getStartingLoopCommand() {
        return startingLoopCommand;
    }
}
