package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Until<R extends Robot> extends LoopCommand<R> {
    private final Environment<R> environment;
    private final String label;

    public Until(String label, int startingLoopIndex, int endingLoopIndex, Environment<R> environment) {
        super(startingLoopIndex, endingLoopIndex);
        this.environment = environment;
        this.label = label;
    }
    @Override
    public RobotCommand getCommandType() {
        return null;
    }

    @Override
    public void run(R robot, double delta_t) {

    }
}
