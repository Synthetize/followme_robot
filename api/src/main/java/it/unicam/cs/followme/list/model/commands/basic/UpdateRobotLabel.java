package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class UpdateRobotLabel extends RunnableCommand implements Command {
    private final Environment environment;
    private final String label;
    private final RobotCommand commandType;

    public UpdateRobotLabel(String label, Environment environment, RobotCommand commandType) {
        this.label = label;
        this.environment = environment;
        this.commandType = commandType;
    }

    @Override
    public RobotCommand getCommandType() {
        return this.commandType;
    }

    @Override
    public void run(Robot robot, double delta_t) {
        switch (this.commandType) {
            case SIGNAL:
                List<Shape> shapes = environment.checkIfRobotIsInsideShapes(robot);
                shapes.forEach(shape -> {
                    if (shape.getConditionLabel().equals(label)) {
                        robot.addLabel(label);
                    }
                });
                addLog(robot);
                break;
            case UNSIGNAL:
                robot.removeLabel(label);
                ModelController.LOGGER.info("UNSIGNAL | " + robot + " removed condition: " + label);
                break;
            default:
                throw new IllegalStateException("Unexpected command type: " + this.commandType);
        }
    }

    private void addLog(Robot robot) {
        if (robot.getCurrentConditionLabels().contains(label))
            ModelController.LOGGER.info("SIGNAL | " + robot + " added condition: " + label);
        else
            ModelController.LOGGER.info("SIGNAL | " + robot + "robot wasn't inside any shape with condition: " + label);

    }

    public String getLabel() {
        return label;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
