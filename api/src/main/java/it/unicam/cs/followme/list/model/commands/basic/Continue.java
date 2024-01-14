package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

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
        ModelController.LOGGER.info("CONTINUE | " + robot + " is executing the continue command for " + seconds + " seconds");
        for(int i=0; i<seconds; i++){
            Coordinate robotCoordinate = environment.getRobotCoordinate(robot);
            double addStepX = robotCoordinate.getX() + robot.getLastMovementDirection().getX();
            double addStepY = robotCoordinate.getY() + robot.getLastMovementDirection().getY();
            environment.setRobotPosition(robot, new CartesianCoordinate(addStepX, addStepY));
            ModelController.LOGGER.info("CONTINUE | " + robot + " moved from " + "(" + String.format("%.3f", robotCoordinate.getX())
                    + ";" + String.format("%.3f", robotCoordinate.getY()) + ")" + " to " + "(" + String.format("%.3f", addStepX) + ";" + String.format("%.3f", addStepY) + ")");
        }
    }

}
