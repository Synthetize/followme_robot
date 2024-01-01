package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;

public abstract class LoopCommand<R extends Robot> implements Command<R> {
    private int startingLoopIndex = 0;
    private int endingLoopIndex = 0;
    private final List<Command<R>> programList;
    public LoopCommand(int startingLoopIndex, int endingLoopIndex, List<Command<R>> programList) {
        this.startingLoopIndex = startingLoopIndex;
        this.endingLoopIndex = endingLoopIndex;
        this.programList = programList;
    }
    public int getStartingLoopIndex() { return startingLoopIndex; }

    public int getEndingLoopIndex() {
        return endingLoopIndex;
    }

    public void setEndingLoopIndex(int index) {
        this.endingLoopIndex = index;
    }
    protected void executeCommand(R robot, double delta_t) {
        int currentCommandIndex = getStartingLoopIndex() + 1;
        while (currentCommandIndex < getEndingLoopIndex()) {
            if (programList.get(currentCommandIndex) instanceof LoopCommand<R> loopCommand) {
                programList.get(currentCommandIndex).run(robot, delta_t);
                currentCommandIndex = loopCommand.getEndingLoopIndex();
            } else {
                programList.get(currentCommandIndex).run(robot, delta_t);
            }
            currentCommandIndex++;
        }
    }
}
