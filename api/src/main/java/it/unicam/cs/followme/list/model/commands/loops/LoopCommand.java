package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

/**
 * This class represents a loop command that is used to execute a block of code
 * multiple times until a certain condition is satisfied
 */
public abstract class LoopCommand implements Command {
    protected int startingLoopIndex;
    protected int endingLoopIndex;

    public LoopCommand(int startingLoopIndex, int endingLoopIndex) {
        this.startingLoopIndex = startingLoopIndex;
        this.endingLoopIndex = endingLoopIndex;
    }

    public int getStartingLoopIndex() {
        return startingLoopIndex;
    }

    public int getEndingLoopIndex() {
        return endingLoopIndex;
    }

    public void setEndingLoopIndex(int endingLoopIndex) {
        this.endingLoopIndex = endingLoopIndex;
    }

    /**
        * This method is used to check if the condition of the loop is satisfied
        * @param robot the robot that is executing the program
        * @return true if the loop should continue, false otherwise
     */
    public abstract boolean isLoopStillRunning(Robot robot);

    public abstract void getLog();
}
