package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.commands.basic.RunnableCommand;
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
    public void init() {
        setSimulationCurrentTime(0);
        robotsList.keySet().forEach(r -> r.setProgram(ProgramCloner.clone(programList)));
    }

    @Override
    public void simulate(double delta_t, double execution_time) {
        setSimulationEndTime(execution_time / delta_t);
        incrementSimulationCurrentTime();
        if (isExecutionOver()) {
            ModelController.LOGGER.info("TIME EXPIRED, PROGRAM EXECUTION STOPPED");
            return;
        }
        for (Robot r : robotsList.keySet()) {
            int robotExecutionIndex = r.getCurrentCommandIndex();
            if (robotExecutionIndex >= programList.size()) {
                ModelController.LOGGER.info("ROBOT " + r + " execution ended");
            } else {
                Command commandToExecute = r.getProgram().get(robotExecutionIndex);
                if (commandToExecute instanceof Done done) {
                    if (done.startingLoopCommand().conditionStatus(r)) {
                        r.setCurrentCommandIndex(done.startingLoopCommand().getStartingLoopIndex());
                    }
                } else if (commandToExecute instanceof RunnableCommand runnableCommand) {
                        runnableCommand.run(r, delta_t);
                }

                r.incrementCurrentCommandIndex();
            }
        }
    }
}
