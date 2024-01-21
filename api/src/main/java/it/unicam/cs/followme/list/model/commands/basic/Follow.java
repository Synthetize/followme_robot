package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Follow extends RunnableCommand {
    private final Environment environment;
    private final String signal;
    private final double distanceFromRobot;
    private final double speed;


    public Follow(String label, double[] args, Environment environment) {
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
    public void run(Robot robot, double delta_t) {
        HashMap<Robot, Coordinate> robotList = getRobotsWithLabelBetweenDistance(robot);
        final AtomicReference<Double> xAvgValue = new AtomicReference<>((double) 0);
        final AtomicReference<Double> yAvgValue = new AtomicReference<>((double) 0);
        if (robotList.isEmpty()) {
            Random random = new Random();
            xAvgValue.updateAndGet(v -> random.nextDouble(2 * this.distanceFromRobot) - this.distanceFromRobot);
            yAvgValue.updateAndGet(v -> random.nextDouble(2 * this.distanceFromRobot) - this.distanceFromRobot);
            xAvgValue.set(2.0);
            yAvgValue.set(2.0);
            ModelController.LOGGER.info("FOLLOW | " + robot + " will move randomly");
        } else {
            for(Map.Entry<Robot, Coordinate> entry : robotList.entrySet()) {
                xAvgValue.updateAndGet(v -> v + entry.getValue().getX());
                yAvgValue.updateAndGet(v -> v + entry.getValue().getY());
            }
            xAvgValue.updateAndGet(v -> v / robotList.size());
            yAvgValue.updateAndGet(v -> v / robotList.size());
            ModelController.LOGGER.info("FOLLOW | " + robot + " will move towards the average position of the robots with label: " + signal + "\n in a range of " + distanceFromRobot + "meters. Avg coordinates: (" + String.format("%.3f", xAvgValue.get()) + ";" + String.format("%.2f", yAvgValue.get()) + ") at speed: " + speed + "m/s");
        }
        Coordinate normalized = calculateNormalizedDirection(xAvgValue.get(), yAvgValue.get(), robot);
        Move move = new Move(normalized, speed, environment);
        move.run(robot, delta_t);
    }

    /*
    * The move command move the robot using the relative coordinates,
    * so we need to calculate the direction using the robot absolute coordinates and the target absolute coordinates
    * and then normalize the result and move the robot in that direction
     */
    private Coordinate calculateNormalizedDirection(Double avgXValue, Double avgYValue, Robot robot) {
        double x = avgXValue - environment.getRobotCoordinate(robot).getX();
        double y = avgYValue - environment.getRobotCoordinate(robot).getY();
        double magnitude = Math.sqrt(x * x + y * y);
        return new CartesianCoordinate(x / magnitude, y / magnitude);
    }

    private HashMap<Robot, Coordinate> getRobotsWithLabelBetweenDistance(Robot robot) {
        Map<Robot, Coordinate> robotList = environment.robotsDetails();
        HashMap<Robot, Coordinate> robotWithLabelBetweenDistance = new HashMap<>();
        robotList.forEach((robotElement, coordinate) -> {
            if (robotElement.equals(robot)) return;
            if (!(robotElement.getCurrentConditionLabels().contains(signal))) return;
            if (environment.getDistanceBetweenTwoCoordinates(coordinate, environment.getRobotCoordinate(robot)) > this.distanceFromRobot)
                return;
            robotWithLabelBetweenDistance.put(robotElement, coordinate);
        });
        return robotWithLabelBetweenDistance;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getLabel() {
        return signal;
    }

    public double[] getArgs() {
        return new double[]{distanceFromRobot, speed};
    }
}
