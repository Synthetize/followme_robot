package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Unsignal implements RunnableCommand {
    private final Environment environment;
    private final String label;

    public Unsignal(String label, Environment environment) {
        this.label = label;
        this.environment = environment;
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.UNSIGNAL;
    }

    @Override
    public void run(Robot robot, double delta_t) {
        robot.removeLabel(label);
        ModelController.LOGGER.info("UNSIGNAL | " + robot + " removed condition: " + label);
    }

    public String getLabel() {
        return label;
    }

    public Environment getEnvironment() {
        return environment;
    }

}
