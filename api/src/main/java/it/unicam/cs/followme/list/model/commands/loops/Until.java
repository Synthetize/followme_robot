package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;


public class Until extends LoopCommand {
    private final Environment environment;
    private final String label;

    public Until(String label, int startingLoopIndex, int endingLoopIndex, Environment environment) {
        super(startingLoopIndex, endingLoopIndex);
        this.label = label;
        this.environment = environment;
    }

    public RobotCommand getCommandType() {
        return RobotCommand.UNTIL;
    }

    @Override
    public final boolean conditionStatus(Robot robot) {
        return environment.checkIfRobotIsInsideShapes(robot).stream()
                .anyMatch(shape -> shape.getConditionLabel().equals(label) && robot.getCurrentConditionLabels().contains(label));
    }

    public String getLabel() {
        return label;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
