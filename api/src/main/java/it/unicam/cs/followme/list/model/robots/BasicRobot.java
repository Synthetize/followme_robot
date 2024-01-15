package it.unicam.cs.followme.list.model.robots;

import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class BasicRobot implements Robot {
    List<Command> program;
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

    @Override
    public void clearCurrentConditionLabels() {
        this.conditionLabels.clear();
    }

    @Override
    public void setProgram(List<Command> commands) {
        this.program = commands;
    }


    @Override
    public String toString() {
        String superString = super.toString();
        int index = superString.indexOf("@");
        String subString = superString.substring(index);
        return "BasicRobot " + subString + " {" + "signaling=" + conditionLabels + '}';
    }
}
