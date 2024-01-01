package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Stop<R extends Robot> implements Command<R> {

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.STOP;
    }

    @Override
    public void run(R robot, double delta_t) {
        robot.setLastMovementDirection(new CartesianCoordinate(0,0));
    }
}
