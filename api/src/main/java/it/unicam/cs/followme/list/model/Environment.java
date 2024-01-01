package it.unicam.cs.followme.list.model;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;

import java.util.List;
import java.util.Map;

/**
 * This interface is used to rappresent a generic environment
 * @param <R> the type of robot
 */
public interface Environment<R extends Robot> {
    /**
     * @return a map that contains the shapes and their coordinates
     */
    Map<Shape, Coordinate> getShapesDetails();
    /**
     * @return a map that contains the robots and their coordinates
     */
    Map<R, Coordinate> getRobotsDetails();

    /**
     * Add the shapes and their coordinates to the environment
     * @param shapes the shapes to add
     * @param coordinates the coordinates of the shapes to add
     */
    void addShapes(List<Shape> shapes, List<Coordinate> coordinates);
    /**
     * Add the robots and their coordinates to the environment
     * @param robots the robots to add
     * @param coordinates the coordinates of the robots to add
     */
    void addRobots(List<R> robots, List<Coordinate> coordinates);
    /**
     * Calculate the distance between two coordinates
     * @param firstCoordinate the first coordinate
     * @param secondCoordinate the second coordinate
     * @return the distance between the two coordinates
     */
    double getDistanceBetweenTwoCoordinates(Coordinate firstCoordinate, Coordinate secondCoordinate);
    /**
     * Check if a robot is inside a shape
     * @param robot the robot to check
     * @return a list of shapes that contains the robot
     */
    List<Shape> checkIfRobotIsInsideShapes(R robot);
    /**
     * Set the position of a robot
     * @param robot the robot to move
     * @param coordinate the new position of the robot
     */
    void setRobotPosition(R robot, Coordinate coordinate);
    /**
     * Get the position of the given robot
     * @param robot the robot to get the position
     * @return the position of the robot
     */
    Coordinate getRobotCoordinate(R robot);
    /**
     * Get the position of the given shape
     * @param shape the shape to get the position
     * @return the position of the shape
     */
    Coordinate getShapeCoordinate(Shape shape);
}

