package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Follow<R extends Robot> implements Command<R> {
    private final Environment<R> environment;
    private final String signal;
    private final int distance;
    private final int speed;

    public Follow(String label, double[] args, Environment<R> environment) {
        this.environment = environment;
        this.signal = label;
        this.distance = (int) args[0];
        this.speed = (int) args[1];
    }

    @Override
    public RobotCommand getCommandType() {
        return RobotCommand.FOLLOW;
    }

    @Override
    public void run(R robot) {
        Map<R, Coordinate> robotList = environment.getRobotsDetails();
        HashMap<R, Coordinate> robotWithLabelBetweenDistance = new HashMap<>();
        robotList.forEach((robotElement, coordinate) -> {
            if (robotElement.getCurrentConditionLabels().contains(signal)) {
                double distance = environment.getDistanceBetweenTwoCoordinates(coordinate, environment.getRobotCoordinate(robot));
                if (distance <= this.distance) {
                    if (!(robotElement.equals(robot))) {
                        robotWithLabelBetweenDistance.put(robotElement, coordinate);
                    }
                }
            }
        });
        final AtomicReference<Double> xAvgValue = new AtomicReference<>((double) 0);
        final AtomicReference<Double> yAvgValue = new AtomicReference<>((double) 0);
        if (robotWithLabelBetweenDistance.isEmpty()) {
            Random random = new Random();
            xAvgValue.updateAndGet(v -> random.nextDouble(2 * this.distance + 1) - this.distance);
            yAvgValue.updateAndGet(v -> random.nextDouble(2 * this.distance + 1) - this.distance);
        } else {
            robotWithLabelBetweenDistance.forEach((shape, coordinate) -> {
                xAvgValue.updateAndGet(v -> v + coordinate.getX());
                yAvgValue.updateAndGet(v -> v + coordinate.getY());
            });
            xAvgValue.updateAndGet(v -> v / robotWithLabelBetweenDistance.size());
            yAvgValue.updateAndGet(v -> v / robotWithLabelBetweenDistance.size());
        }
        Move<R> move = new Move<>(new CartesianCoordinate(xAvgValue.get(), yAvgValue.get()), speed, environment);
        move.run(robot);
    }
}
