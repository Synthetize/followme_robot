package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

public abstract class LoopCommand<R extends Robot> implements Command<R> {
    private int startingLoopIndex = 0;
    private int endingLoopIndex = 0;

    public LoopCommand(int startingLoopIndex, int endingLoopIndex) {
        this.startingLoopIndex = startingLoopIndex;
        this.endingLoopIndex = endingLoopIndex;
    }
    public int getStartingLoopIndex() { return startingLoopIndex; }

    public int getEndingLoopIndex() {
        return endingLoopIndex;
    }

    public void setEndingLoopIndex(int index) {
        this.endingLoopIndex = index;
    }
}
