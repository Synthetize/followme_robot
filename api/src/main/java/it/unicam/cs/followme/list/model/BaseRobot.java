package it.unicam.cs.followme.list.model;

import java.util.ArrayList;
import java.util.List;

public class BaseRobot implements Robot{

    List<String> conditionLabels;

    //lastMovement Direction
    //lastMovement Speed

    public BaseRobot() {
        this.conditionLabels = new ArrayList<>();
    }
    @Override
    public void move(Coordinate currentPosition, Coordinate targetPosition, int speed) {

    }

    @Override
    public void moveRandomly(Coordinate currentPosition, List<Coordinate> randomCoordinateRange, int speed) {

    }

    @Override
    public void signalConditionLabel(String conditionLabel) {

    }

    @Override
    public void unsignalConditionLabel(String conditionLabel) {

    }

    @Override
    public void follow(Coordinate currentPosition, List<Coordinate> robots, double renge, int speed) {

    }

    @Override
    public void continueMove(Coordinate currentPosition, Coordinate targetPosition, int speed) {

    }

    @Override
    public void stop() {

    }
}
