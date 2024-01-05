package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.utils.SimulationTimer;

import java.util.List;
import java.util.Map;

public class RobotSimulator<R extends Robot> extends SimulationTimer implements Simulator<R> {
    private final List<Command<R>> programList;
    protected int currentCommandIndex = 0;
    private final Map<R, Coordinate> robotsList;

    public RobotSimulator(List<Command<R>> programList, Map<R, Coordinate> robotsList) {
        this.robotsList = robotsList;
        this.programList = programList;
    }

    @Override
    public void setProgramList(List<Command<R>> programList) {
        System.out.println("Setting program list");
        System.out.println(programList.size());
        this.programList.addAll(programList);
        System.out.println(this.programList.size());
    }

    @Override
    public void simulate(double delta_t, double execution_time) {
        for(R r : robotsList.keySet()) {
            runProgram(r, delta_t, execution_time);
        }
    }

    private void runProgram(R robot, double delta_t, double execution_time) {
        setSimulationCurrentTime(0);
        setSimulationEndTime(numberOfCommandsThatCanBeExecuted(execution_time, delta_t));
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
