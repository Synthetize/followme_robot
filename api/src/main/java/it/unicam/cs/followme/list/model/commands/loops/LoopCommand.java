package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.commands.Command;

public abstract class LoopCommand implements Command {
    private int endingLoopIndex = 0;

    public int getEndingLoopIndex() {
        return endingLoopIndex;
    }
    public int setEndingLoopIndex(int index) {
        return this.endingLoopIndex = index;
    }



}
