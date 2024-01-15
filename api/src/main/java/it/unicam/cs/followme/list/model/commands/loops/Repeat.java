package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Repeat<R extends Robot> extends LoopCommand<R> {

    private int repetitionNumbers;

    public Repeat(int repetitionNumbers, int startingLoopIndex, int endingLoopIndex) {
        super(startingLoopIndex, endingLoopIndex);
        this.repetitionNumbers = repetitionNumbers;
    }

    @Override
    public RobotCommand getCommandType() {
        if (repetitionNumbers == -1)
            return RobotCommand.FOREVER;
        return RobotCommand.REPEAT;
    }

    @Override
    public void run(R robot, double delta_t) {
    }

    @Override
    public final boolean conditionStatus(R robot) {
        if (repetitionNumbers == -1) {
            return true;
        }
        if (repetitionNumbers > 0) {
            repetitionNumbers--;
            return true;
        }
        return false;
    }
}
