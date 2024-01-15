package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Repeat extends LoopCommand {

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
    public void run(Robot robot, double delta_t) {
    }

    @Override
    public final boolean conditionStatus(Robot robot) {
        if (repetitionNumbers == -1) {
            return true;
        }
        repetitionNumbers--;
        if (repetitionNumbers > 0) {
            return true;
        }
        return false;
    }

    public int getRepetitions() {
        return repetitionNumbers;
    }

    public int getStartingIndex() {
        return startingLoopIndex;
    }

    public int getEndingIndex() {
        return endingLoopIndex;
    }
}
