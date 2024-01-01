package it.unicam.cs.followme.list.model.robots;

import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class BasicRobot implements Robot {

    List<String> conditionLabels;
    Coordinate lastMovementDirection;

    // TODO: 12/11/2023 da cambiare costruttore
    public BasicRobot() {
        this.conditionLabels = new ArrayList<>();
        this.lastMovementDirection = new CartesianCoordinate(0,0);
    }

    @Override
    public List<String> getCurrentConditionLabels() {
        return this.conditionLabels;
    }

    @Override
    public void addLabel(String label) {
        this.conditionLabels.add(label);
    }

    @Override
    public void removeLabel(String label) {
        this.conditionLabels.remove(label);
    }

    @Override
    public Coordinate getLastMovementDirection() {
        return this.lastMovementDirection;
    }

    @Override
    public void setLastMovementDirection(Coordinate direction) {
        this.lastMovementDirection = direction;
    }
}
