package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.utils.ProgramCloner;
import it.unicam.cs.followme.list.utils.SimulationTimer;

import java.util.List;
import java.util.Map;

public class RobotSimulator extends SimulationTimer implements Simulator {
    private final List<Command> programList;
    protected final Map<Robot, Coordinate> robotsList;

    public RobotSimulator(List<Command> programList, Map<Robot, Coordinate> robotsList) {
        this.robotsList = robotsList;
        this.programList = programList;
    }

    @Override
    public void setProgramList(List<Command> programList) {
        this.programList.addAll(programList);
    }

    @Override
    public void simulate(double delta_t, double execution_time) {
        setSimulationEndTime(execution_time/delta_t);
        for (Robot r : robotsList.keySet()) {
            ModelController.LOGGER.info("STARTING SIMULATION FOR ROBOT " + r);
            setSimulationCurrentTime(0);
            r.setProgram(ProgramCloner.clone(programList));
            runRobotProgram(r, delta_t);
        }
        ModelController.LOGGER.info("ROBOT EXECUTION FINISHED");
    }

    void runRobotProgram(Robot r, double delta_t) {
        while (r.getCurrentCommandIndex() < r.getProgram().size()) {
            int executionIndex = r.getCurrentCommandIndex();
            Command commandToExecute = r.getProgram().get(executionIndex);
            if (commandToExecute instanceof Done done) {
                if (done.getStartingLoopCommand().conditionStatus(r)) {
                    r.setCurrentCommandIndex(done.getStartingLoopCommand().getStartingLoopIndex());
                }
            } else {
                commandToExecute.run(r, delta_t);
            }
            incrementSimulationCurrentTime();
            if(isExecutionOver()) {
                ModelController.LOGGER.info("TIME EXPIRED, PROGRAM EXECUTION STOPPED");
                break;
            }
            r.incrementCurrentCommandIndex();
        }
    }
}
