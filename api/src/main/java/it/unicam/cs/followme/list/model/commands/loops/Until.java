package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Until<R extends Robot> extends LoopCommand<R> {
    private final Environment<R> environment;
    private final String label;

    public Until(String label, int startingLoopIndex, int endingLoopIndex, Environment<R> environment,  List<Command<R>> programList) {
        super(startingLoopIndex, endingLoopIndex, programList);
        this.environment = environment;
        this.label = label;
    }
    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.UNTIL;
    }

    @Override
    public void run(R robot, double delta_t) {
        while (conditionStatus(robot)) {
            executeCommand(robot, delta_t);
        }
    }

    private boolean conditionStatus(R robot){
        return environment.checkIfRobotIsInsideShapes(robot).stream()
                .anyMatch(shape -> shape.getConditionLabel().equals(label) && robot.getCurrentConditionLabels().contains(label));
    }
}
