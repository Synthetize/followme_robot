package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.utils.SimulationTimer;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;

public abstract class LoopCommand extends SimulationTimer implements Command {
    protected int startingLoopIndex = 0;
    protected int endingLoopIndex = 0;

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
