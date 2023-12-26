package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class UpdateRobotLabel<R extends Robot> implements Command<R> {
    private final Environment<R> environment;
    private final String label;
    private final RobotCommand commandType;

    public UpdateRobotLabel(String label, Environment<R> environment, RobotCommand commandType) {
        this.label = label;
        this.environment = environment;
        this.commandType = commandType;
    }

    @Override
    public RobotCommand getCommandType() {
        return this.commandType;
    }

    @Override
    public void run(R robot, double delta_t) {
        switch (this.commandType) {
            case SIGNAL:
                List<Shape> shapes = environment.checkIfRobotIsInsideShapes(robot);
                shapes.forEach(shape -> {
                    if (shape.getConditionLabel().equals(label)) {
                        robot.addLabel(label);
                    }
                });
                break;
            case UNSIGNAL:
                robot.removeLabel(label);
                break;
            default:
                throw new IllegalStateException("Unexpected command type: " + this.commandType);
        }
    }
}
