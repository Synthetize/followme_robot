package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.utils.HandleCommandToExecute;
import it.unicam.cs.followme.list.utils.SimulationTimer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RobotSimulator<R extends Robot> extends SimulationTimer implements Simulator<R> {
    private final List<Command<R>> programList;
    protected AtomicInteger currentCommandIndex = new AtomicInteger(0);
    protected final Map<R, Coordinate> robotsList;

    public RobotSimulator(List<Command<R>> programList, Map<R, Coordinate> robotsList) {
        this.robotsList = robotsList;
        this.programList = programList;
    }

    @Override
    public void setProgramList(List<Command<R>> programList) {
        this.programList.addAll(programList);
    }

//    @Override
//    public void simulate(double delta_t, double execution_time) {
//        for (R r : robotsList.keySet()) {
//            ModelController.LOGGER.info("RUNNING PROGRAM FOR ROBOT: " + r);
//            runProgram(r, delta_t, execution_time);
//        }
//    }
//
//    private void runProgram(R robot, double delta_t, double execution_time) {
//        currentCommandIndex.set(0);
//        setSimulationCurrentTime(0);
//        setSimulationEndTime(numberOfCommandsThatCanBeExecuted(execution_time, delta_t));
//        while (currentCommandIndex.get() < programList.size()) {
//            HandleCommandToExecute<R> handleCommandToExecute = new HandleCommandToExecute<>(currentCommandIndex, programList);
//            handleCommandToExecute.findLoopOrBasicCommandAndCallRun(delta_t, robot, programList.size());
//        }
//    }

    @Override
    public void simulate(double delta_t, double execution_time) {
        currentCommandIndex.set(0);
        for (R r : robotsList.keySet()) {
            r.setProgram(programList);
        }
    }
}
