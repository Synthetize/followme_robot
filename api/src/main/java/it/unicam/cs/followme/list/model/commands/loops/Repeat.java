package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.PrimitiveIterator;

public class Repeat extends LoopCommand {

    private int repetitionNumbers;
    private int currentRepetitionNumber = 0;

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
    public final boolean isLoopStillRunning(Robot robot) {
        if (repetitionNumbers == -1) {
            return true;
        }
        currentRepetitionNumber++;
        if (currentRepetitionNumber >= repetitionNumbers) {
            currentRepetitionNumber = 0;
            return false;
        }
        return true;
    }

    @Override
    public void getLog() {
        if (repetitionNumbers == -1)
            ModelController.LOGGER.info("REPEAT | repeat forever");
        else
            ModelController.LOGGER.info("REPEAT | repeating " + repetitionNumbers + " times");
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
