package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Continue implements RunnableCommand {

    private final int seconds;
    private final Environment environment;

    public Continue(int seconds, Environment environment) {
        this.seconds = seconds;
        this.environment = environment;
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.CONTINUE;
    }

    /**
     * Executes the continue command for a given robot.
     *
     * This method calculates get the robot's last movement values and applies them to the robot's current position
     * for the given number of seconds.
     */
    @Override
    public void run(Robot robot, double delta_t) {
        ModelController.LOGGER.info("CONTINUE | " + robot + " is executing the continue command for " + seconds + " seconds");
        for (int i = 0; i < seconds; i++) {
            Coordinate robotCoordinate = environment.getRobotCoordinate(robot);
            double addStepX = robotCoordinate.getX() + robot.getLastMovementValues().getX();
            double addStepY = robotCoordinate.getY() + robot.getLastMovementValues().getY();
            environment.setRobotPosition(robot, new CartesianCoordinate(addStepX, addStepY));
            ModelController.LOGGER.info("CONTINUE | " + robot + " moved from " + "(" + String.format("%.3f", robotCoordinate.getX())
                    + ";" + String.format("%.3f", robotCoordinate.getY()) + ")" + " to " + "(" + String.format("%.3f", addStepX) + ";" + String.format("%.3f", addStepY) + ")");
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public Environment getEnvironment() {
        return environment;
    }

}
