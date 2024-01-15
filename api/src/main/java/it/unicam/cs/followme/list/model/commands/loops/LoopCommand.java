package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.utils.SimulationTimer;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

public abstract class LoopCommand extends SimulationTimer implements Command {
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

    public abstract boolean conditionStatus(Robot robot);

}
