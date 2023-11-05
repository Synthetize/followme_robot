package it.unicam.cs.followme.list.model.robots;

import it.unicam.cs.followme.list.model.utils.MovementDirection;
import java.util.ArrayList;
import java.util.List;

public class BasicRobot implements Robot {

    List<String> conditionLabels;
    MovementDirection lastMovementDirection;

    public BasicRobot() {
        this.conditionLabels = new ArrayList<>();
        this.lastMovementDirection = null;
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
    public MovementDirection getLastMovementDirection() {
        return this.lastMovementDirection;
    }

    @Override
    public void setLastMovementDirection(MovementDirection direction) {
        this.lastMovementDirection = direction;
    }
}
