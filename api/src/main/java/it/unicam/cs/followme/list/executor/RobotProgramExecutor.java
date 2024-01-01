package it.unicam.cs.followme.list.executor;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;

public class RobotProgramExecutor<R extends Robot> extends ExecutionTimer implements ProgramExecutor<R> {
    private final List<Command<R>> programList;
    protected int currentCommandIndex = 0;

    public RobotProgramExecutor(List<Command<R>> programList/*, R robot, Environment<R> environment*/) {
        super(0, 0);
        this.programList = programList;
    }

    @Override
    public void setProgramList(List<Command<R>> programList) {
        this.programList.addAll(programList);
    }

    @Override
    public void executeProgram(R robot, double delta_t, double execution_time) {
        setEndTime(numberOfCommandsThatCanBeExecuted(execution_time, delta_t));
        while (currentCommandIndex < programList.size()) {
            if(incrementTimerIfNotOver()) {
                stopExecution();
                return;
            }
            if (programList.get(currentCommandIndex) instanceof LoopCommand<R> loopCommand) {
                programList.get(currentCommandIndex).run(robot, delta_t);
                currentCommandIndex = loopCommand.getEndingLoopIndex();
            } else {
                programList.get(currentCommandIndex).run(robot, delta_t);
            }
            currentCommandIndex++;
        }
    }

    private long numberOfCommandsThatCanBeExecuted (double execution_time, double delta_t) {
        return Math.round(execution_time / delta_t);
    }

    private void stopExecution() {
        currentCommandIndex = programList.size();
    }
}
