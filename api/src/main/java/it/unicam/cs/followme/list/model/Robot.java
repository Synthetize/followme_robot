package it.unicam.cs.followme.list.model;

import java.util.List;

public interface Robot {
    void move(Coordinate currentPosition, Coordinate targetPosition, int speed);

    void moveRandomly(Coordinate currentPosition, List<Coordinate> randomCoordinateRange, int speed);

    void signalConditionLabel(String conditionLabel);

    void unsignalConditionLabel(String conditionLabel);

    void follow(Coordinate currentPosition ,List<Coordinate> robots, double renge, int speed);

    void continueMove(Coordinate currentPosition, Coordinate targetPosition, int speed);

    void stop();

}
