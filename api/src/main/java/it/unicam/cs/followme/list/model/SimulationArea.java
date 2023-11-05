package it.unicam.cs.followme.list.model;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.CircleShape;
import it.unicam.cs.followme.list.model.shapes.RectangleShape;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationArea<R extends Robot> implements Environment<R> {

    private final Map<Shape, Coordinate> shapesDetails;
    private final Map<R, Coordinate> robotsDetails;

    public SimulationArea(Map<Shape, Coordinate> shapesDetails, Map<R, Coordinate> robotsDetails) {
        this.shapesDetails = shapesDetails;
        this.robotsDetails = robotsDetails;
    }

    @Override
    public Map<Shape, Coordinate> getShapesDetails() {
        return this.shapesDetails;
    }

    @Override
    public Map<R, Coordinate> getRobotsDetails() {
        return this.robotsDetails;
    }

    @Override
    public void addShapes(List<Shape> shapes, List<Coordinate> coordinates) {
        shapes.forEach(shape -> {
            shapesDetails.put(shape, coordinates.get(shapes.indexOf(shape)));
        });
    }
    @Override
    public void addRobots(List<R> robots, List<Coordinate> coordinates) {
        robots.forEach(robot -> {
            robotsDetails.put(robot, coordinates.get(robots.indexOf(robot)));
        });
    }


    // TODO: 05/11/2023 faccio lo stesso calcolo in lastrobotdirection
    @Override
    public double getDistanceBetweenTwoCoordinates(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        return Math.sqrt(Math.pow(firstCoordinate.getX() - secondCoordinate.getX(), 2) + Math.pow(firstCoordinate.getY() - secondCoordinate.getY(), 2));
    }

    //todo: scrivere test e controllare condizione per il triangolo
    @Override
    public List<Shape> checkIfRobotIsInsideShapes(R robot) {
        Map.Entry<R, Coordinate> robotEntry = this.robotsDetails.entrySet()
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
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


}


