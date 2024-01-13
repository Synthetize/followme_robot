package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.utils.HandleCommandToExecute;
import it.unicam.cs.followme.list.utils.SimulationTimer;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LoopCommand<R extends Robot> extends SimulationTimer implements Command<R> {
    private int startingLoopIndex = 0;
    private int endingLoopIndex = 0;
    private final List<Command<R>> programList;
    protected AtomicInteger currentCommandIndex = new AtomicInteger(0);
    private HandleCommandToExecute<R> handleCommandToExecute;

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
    protected void executeCommand(R robot, double delta_t){
        currentCommandIndex.set(getStartingLoopIndex() + 1);
        while (currentCommandIndex.get() < getEndingLoopIndex()) {
            handleCommandToExecute = new HandleCommandToExecute<>(currentCommandIndex, programList);
            handleCommandToExecute.findLoopOrBasicCommandAndCallRun(delta_t, robot, getEndingLoopIndex());
        }
    }

}
