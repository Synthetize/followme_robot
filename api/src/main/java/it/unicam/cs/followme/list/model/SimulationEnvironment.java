package it.unicam.cs.followme.list.model;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record SimulationEnvironment(Map<Shape, Coordinate> shapesDetails,
                                    Map<Robot, Coordinate> robotsDetails) implements Environment {

    @Override
    public void addShapes(List<Shape> shapes, List<Coordinate> coordinates) {
        shapes.forEach(shape -> shapesDetails.put(shape, coordinates.get(shapes.indexOf(shape))));
    }

    @Override
    public void addRobots(List<Robot> robots, List<Coordinate> coordinates) {
        robots.forEach(robot -> robotsDetails.put(robot, coordinates.get(robots.indexOf(robot))));
    }

    @Override
    public double getDistanceBetweenTwoCoordinates(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        return Math.sqrt(Math.pow(firstCoordinate.getX() - secondCoordinate.getX(), 2) + Math.pow(firstCoordinate.getY() - secondCoordinate.getY(), 2));
    }

    @Override
    public List<Shape> checkIfRobotIsInsideShapes(Robot robot) {
        Map.Entry<Robot, Coordinate> robotEntry = this.robotsDetails.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(robot))
                .findFirst().orElse(null);
        if (robotEntry == null) {
            return new ArrayList<>();
        }
        return this.shapesDetails.entrySet()
                .stream()
                .filter(shapeEntry -> {
                    double distanceBetweenRobotAndShapeCenter = getDistanceBetweenTwoCoordinates(robotEntry.getValue(), shapeEntry.getValue());
                    if (shapeEntry.getKey() instanceof RectangleShape rectangleShape) {
                        return checkIfRobotIsInsideRectangleShape(robotEntry.getValue(), Map.entry(rectangleShape, shapeEntry.getValue()));
                    } else if (shapeEntry.getKey() instanceof CircleShape circleShape) {
                        return circleShape.radius() >= distanceBetweenRobotAndShapeCenter;
                    }
                    return false;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void setRobotPosition(Robot robot, Coordinate coordinate) {
        this.robotsDetails.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(robot))
                .findFirst()
                .ifPresent(entry -> entry.setValue(coordinate));
    }

    @Override
    public Coordinate getRobotCoordinate(Robot robot) {
        return this.robotsDetails.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(robot))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    @Override
    public Coordinate getShapeCoordinate(Shape shape) {
        return this.shapesDetails.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(shape))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    private boolean checkIfRobotIsInsideRectangleShape(Coordinate robotCoordinate, Map.Entry<RectangleShape, Coordinate> shapeEntry) {
        Coordinate rectangleCenter = shapeEntry.getValue();
        RectangleShape rectangleShape = shapeEntry.getKey();
        double topLeftAngleX = rectangleCenter.getX() - rectangleShape.width() / 2;
        double topLeftAngleY = rectangleCenter.getY() + rectangleShape.height() / 2;
        double bottomRightAngleX = rectangleCenter.getX() + rectangleShape.width() / 2;
        double bottomRightAngleY = rectangleCenter.getY() - rectangleShape.height() / 2;
        return topLeftAngleX <= robotCoordinate.getX() && robotCoordinate.getX() <= bottomRightAngleX
                && bottomRightAngleY <= robotCoordinate.getY() && robotCoordinate.getY() <= topLeftAngleY;
    }
}


