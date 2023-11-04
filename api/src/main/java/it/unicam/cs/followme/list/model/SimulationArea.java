package it.unicam.cs.followme.list.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationArea implements Environment<CartesianCoordinate> {

    private final Map<Shape, CartesianCoordinate> shapesDetails;
    private final Map<Robot, CartesianCoordinate> robotsDetails;

    public SimulationArea() {
        this.shapesDetails = new HashMap<>();
        this.robotsDetails = new HashMap<>();
    }


    @Override
    public Map<Shape, CartesianCoordinate> getShapesDetails() {
        return this.shapesDetails;
    }

    @Override
    public Map<Robot, CartesianCoordinate> getRobotsDetails() {
        return this.robotsDetails;
    }

    @Override
    public void addShapes(List<Shape> shapes, List<CartesianCoordinate> coordinates) {
      shapes.forEach(shape -> {
          shapesDetails.put(shape, coordinates.get(shapes.indexOf(shape)));
      });
    }
    @Override
    public void addRobots(List<Robot> robots, List<CartesianCoordinate> coordinates) {
        robots.forEach(robot -> {
            robotsDetails.put(robot, coordinates.get(robots.indexOf(robot)));
        });
    }

    //todo: scrivere test e controllare condizione per il triangolo
    @Override
    public HashMap<Shape, CartesianCoordinate> isTheRobotInsideAShape(Robot robot) {
        Map.Entry<Robot, CartesianCoordinate> robotEntry = this.robotsDetails.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(robot))
                .findFirst().orElse(null);
        if (robotEntry == null) {
            return new HashMap<>();
        }
        return this.shapesDetails.entrySet()
                .stream()
                .filter(shapeEntry -> {
                    double distanceBetweenRobotAndShapeCenter = getDistanceBetweenTwoCoordinates(robotEntry.getValue(), shapeEntry.getValue());
                    if (shapeEntry.getKey() instanceof RectangleShape rectangleShape) {
                        if (rectangleShape.getShapeArea() >= distanceBetweenRobotAndShapeCenter) { // da controllare
                            return true;
                        }
                    } else if (shapeEntry.getKey() instanceof CircleShape circleShape) {
                        if (circleShape.getRadius() >= distanceBetweenRobotAndShapeCenter) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }

    @Override
    public double getDistanceBetweenTwoCoordinates(CartesianCoordinate firstCoordinate, CartesianCoordinate secondCoordinate) {
        return Math.sqrt(Math.pow(firstCoordinate.xValue() - secondCoordinate.xValue(), 2) + Math.pow(firstCoordinate.yValue() - secondCoordinate.yValue(), 2));
    }
}


