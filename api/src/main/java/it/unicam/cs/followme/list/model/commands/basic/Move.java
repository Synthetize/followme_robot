package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
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
    public void run(R robot) {
        double distance = environment.getDistanceBetweenTwoCoordinates(targetCoordinates, environment.getRobotCoordinate(robot));
        double time = distance / speed;
        int numberOfSteps = (int) (time * 1);
        double stepX = (targetCoordinates.getX() - environment.getRobotCoordinate(robot).getX()) / numberOfSteps;
        double stepY = (targetCoordinates.getY() - environment.getRobotCoordinate(robot).getY()) / numberOfSteps;
        robot.setLastMovementDirection(new CartesianCoordinate(stepX, stepY));
        System.out.println("stepX: " + stepX + " stepY: " + stepY);
        for (int i = 1; i <= numberOfSteps; i++) {
            Coordinate currentPosition = environment.getRobotCoordinate(robot);
            environment.setRobotPosition(robot, new CartesianCoordinate(currentPosition.getX() + stepX, currentPosition.getY() + stepY));
        }
    }
}
