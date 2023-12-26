package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Repeat<R extends Robot> extends LoopCommand<R>{

    private final int repetitionNumbers;
    private final Environment<R> environment;
    public Repeat(int repetitionNumbers, int startingLoopIndex, int endingLoopIndex, Environment<R> environment) {
        super(startingLoopIndex,endingLoopIndex);
        this.repetitionNumbers = repetitionNumbers;
        this.environment = environment;
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.REPEAT;
    }

    @Override
    public void run(Robot robot, double delta_t) {
    }
}
