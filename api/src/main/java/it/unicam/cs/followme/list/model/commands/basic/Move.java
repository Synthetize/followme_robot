package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Move<R extends Robot> implements Command<R> {
    private final Coordinate targetCoordinates;
    private final double speed;
    private final Environment<R> environment;

    public Move(Coordinate targetCoordinates, double speed, Environment<R> environment) {
        this.targetCoordinates = targetCoordinates;
        this.speed = speed;
        this.environment = environment;
    }


    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.MOVE;
    }

    @Override
    public void run(R robot, double delta_t) {
        Coordinate robotRelativeCoordinates = new CartesianCoordinate(0, 0);
        double distance = environment.getDistanceBetweenTwoCoordinates(targetCoordinates, robotRelativeCoordinates);
        // Calculate the change in position
        double delta_d = speed * delta_t;
        // Calculate the ratio to get the proportion of the change along the desired direction
        double ratio = delta_d / distance;
        // Apply the ratio to get the change along the desired direction
        double deltaX = (targetCoordinates.getX() - robotRelativeCoordinates.getX()) * ratio;
        double deltaY = (targetCoordinates.getY() - robotRelativeCoordinates.getY()) * ratio;
        environment.setRobotPosition(robot, new CartesianCoordinate(
                environment.getRobotCoordinate(robot).getX() + deltaX,
                environment.getRobotCoordinate(robot).getY() + deltaY
        ));
        robot.setLastMovementDirection(new CartesianCoordinate(deltaX, deltaY));
    }
}
