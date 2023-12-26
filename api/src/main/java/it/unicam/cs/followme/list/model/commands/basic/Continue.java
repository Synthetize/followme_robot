package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.net.CookieHandler;

public class Continue<R extends Robot> implements Command<R> {

    private final int seconds;
    private final Environment<R> environment;
    public Continue(int seconds, Environment<R> environment) {
        this.seconds = seconds;
        this.environment = environment;
    }
    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.CONTINUE;
    }
    @Override
    public void run(R robot, double delta_t) {
        for(int i=0; i<seconds; i++){
            //TODO: 1 second = 1 step, system pause needed?
            Coordinate robotCoordinate = environment.getRobotCoordinate(robot);
            double addStepX = robotCoordinate.getX() + robot.getLastMovementDirection().getX();
            double addStepY = robotCoordinate.getY() + robot.getLastMovementDirection().getY();
            environment.setRobotPosition(robot, new CartesianCoordinate(addStepX, addStepY));
        }
    }
}
