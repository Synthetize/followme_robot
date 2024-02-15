package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Signal implements Command, RunnableCommand {
    private final Environment environment;
    private final String label;

    public Signal(String label, Environment environmente) {
        this.label = label;
        this.environment = environmente;
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.SIGNAL;
    }

    @Override
    public void run(Robot robot, double delta_t) {
        List<Shape> shapes = environment.checkIfRobotIsInsideShapes(robot);
        shapes.forEach(shape -> {
            if (shape.getConditionLabel().equals(label)) {
                if (!robot.getCurrentConditionLabels().contains(label)) {
                    robot.addLabel(label);
                }
            }
        });
        addLog(robot);
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
