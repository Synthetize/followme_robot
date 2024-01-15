package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Stop implements Command {

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.STOP;
    }

    @Override
    public void run(Robot robot, double delta_t) {
        robot.setLastMovementDirection(new CartesianCoordinate(0,0));
        robot.clearCurrentConditionLabels();
        ModelController.LOGGER.info("STOP | " + robot + " last movement direction set to (0,0) and current condition labels cleared");
    }

}
