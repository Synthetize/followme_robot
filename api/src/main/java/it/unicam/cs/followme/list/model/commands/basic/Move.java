package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Move implements Command {
    private final Coordinate targetCoordinates;
    private final double speed;
    private final Environment environment;

    public Move(Coordinate targetCoordinates, double speed, Environment environment) {
        this.targetCoordinates = targetCoordinates;
        this.speed = speed;
        this.environment = environment;
    }


    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.MOVE;
    }

    @Override
    public void run(Robot robot, double delta_t) {
        Coordinate robotRelativeCoordinates = new CartesianCoordinate(0, 0);
        double distance = environment.getDistanceBetweenTwoCoordinates(targetCoordinates, robotRelativeCoordinates);
        // Calculate the change in position
        double delta_d = speed * delta_t;
        // Calculate the ratio to get the proportion of the change along the desired direction
        double ratio = delta_d / distance;
        // Apply the ratio to get the change along the desired direction
        double deltaX = (targetCoordinates.getX() - robotRelativeCoordinates.getX()) * ratio;
        double deltaY = (targetCoordinates.getY() - robotRelativeCoordinates.getY()) * ratio;
        double currentX = environment.getRobotCoordinate(robot).getX();
        double currentY = environment.getRobotCoordinate(robot).getY();
        environment.setRobotPosition(robot, new CartesianCoordinate(
                currentX + deltaX,
                currentY + deltaY
        ));
        robot.setLastMovementDirection(new CartesianCoordinate(deltaX, deltaY));
        addLog(robot, deltaX, deltaY, currentX, currentY);
    }

    private void addLog(Robot robot, double deltaX, double deltaY, double currentX, double currentY) {
        ModelController.LOGGER.info("MOVE | " + robot + " moved from " + "(" + String.format("%.3f", currentX)
                + ";" + String.format("%.3f", currentY) + ")" + " to " + "(" + String.format("%.3f", currentX + deltaX) + ";" + String.format("%.3f", currentY + deltaY) + ")");
    }

    public Coordinate getCoordinate() {
        return targetCoordinates;
    }

    public double getSpeed() {
        return speed;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
