package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Until<R extends Robot> extends LoopCommand<R> {
    private final Environment<R> environment;
    private final String label;

    public Until(String label, int startingLoopIndex, int endingLoopIndex, Environment<R> environment) {
        super(startingLoopIndex, endingLoopIndex);
        this.label = label;
        this.environment = environment;
    }

    public RobotCommand getCommandType() {
        return RobotCommand.UNTIL;
    }

    public void run(R robot, double delta_t) {}

    @Override
    public final boolean conditionStatus(R robot ) {
        return environment.checkIfRobotIsInsideShapes(robot).stream()
                .anyMatch(shape -> shape.getConditionLabel().equals(label) && robot.getCurrentConditionLabels().contains(label));
    }


}
