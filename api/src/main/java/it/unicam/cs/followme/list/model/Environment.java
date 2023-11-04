package it.unicam.cs.followme.list.model;

import java.util.List;
import java.util.Map;

public interface Environment<C extends Coordinate> {
    Map<Shape, C> getShapesDetails();

    Map<Robot, C> getRobotsDetails();

    void addShapes(List<Shape> shapes, List<C> coordinates);

    void addRobots(List<Robot> robots, List<C> coordinates);

    Map<Shape, C> isTheRobotInsideAShape(Robot robot);

    double getDistanceBetweenTwoCoordinates(C firstCoordinate, C secondCoordinate);
}
