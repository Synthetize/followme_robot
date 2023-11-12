package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Repeat_DoForever extends LoopCommand {
    private final RobotCommand commandType;
    //if -1, it means that the loop is infinite
    private final int repetitionNumbers;
    private int endingLoopIndex;
    public Repeat_DoForever(RobotCommand commandType, int repetitionNumbers) {
        this.commandType = commandType;
        this.repetitionNumbers = repetitionNumbers;
    }
    @Override
    public RobotCommand getCommandType() {
        return commandType;
    }

    @Override
    public void run(Robot robot) {

    }

    public int getNumbersOfRepetitions() {
        return repetitionNumbers;
    }

}
