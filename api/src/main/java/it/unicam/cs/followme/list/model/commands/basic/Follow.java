package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Follow<R extends Robot> implements Command<R> {
    private final Environment<R> environment;
    private final String signal;
    private final double distanceFromRobot;
    private final double speed;


    public Follow(String label, double[] args, Environment<R> environment) {
        this.environment = environment;
        this.signal = label;
        this.distanceFromRobot = args[0];
        this.speed = args[1];
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.FOLLOW;
    }

    @Override
    public void run(R robot, double delta_t) {
        HashMap<R, Coordinate> robotList = getRobotsWithLabelBetweenDistance(robot);
        final AtomicReference<Double> xAvgValue = new AtomicReference<>((double) 0);
        final AtomicReference<Double> yAvgValue = new AtomicReference<>((double) 0);
        if (robotList.isEmpty()) {
            Random random = new Random();
            xAvgValue.updateAndGet(v -> random.nextDouble(2 * this.distanceFromRobot) - this.distanceFromRobot);
            yAvgValue.updateAndGet(v -> random.nextDouble(2 * this.distanceFromRobot) - this.distanceFromRobot);
        } else {
            robotList.forEach((shape, coordinate) -> {
                xAvgValue.updateAndGet(v -> v + coordinate.getX());
                yAvgValue.updateAndGet(v -> v + coordinate.getY());
            });
            xAvgValue.updateAndGet(v -> v / robotList.size());
            yAvgValue.updateAndGet(v -> v / robotList.size());
        }
        Move<R> move = new Move<>(new CartesianCoordinate(xAvgValue.get(), yAvgValue.get()), speed, environment);
        move.run(robot, delta_t);
    }

    private HashMap<R, Coordinate> getRobotsWithLabelBetweenDistance(R robot) {
        Map<R, Coordinate> robotList = environment.getRobotsDetails();
        HashMap<R, Coordinate> robotWithLabelBetweenDistance = new HashMap<>();
        robotList.forEach((robotElement, coordinate) -> {
            if(robotElement.equals(robot)) return;
            if(!(robotElement.getCurrentConditionLabels().contains(signal))) return;
            if(environment.getDistanceBetweenTwoCoordinates(coordinate, environment.getRobotCoordinate(robot)) > this.distanceFromRobot) return;
            robotWithLabelBetweenDistance.put(robotElement, coordinate);
        });
        return robotWithLabelBetweenDistance;
    }
}
