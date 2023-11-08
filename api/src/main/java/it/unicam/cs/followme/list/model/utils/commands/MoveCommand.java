package it.unicam.cs.followme.list.model.utils.commands;

import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.Random;

public class MoveCommand implements Command{
    private final RobotCommand commandType;
    private final Coordinate targetCoordinates;
    private final int speed;

    public MoveCommand(Coordinate targetCoordinates, int speed) {
        this.commandType = RobotCommand.MOVE;
        this.targetCoordinates = targetCoordinates;
        this.speed = speed;
    }

    @Override
    public RobotCommand getCommandType() {
        return commandType;
    }

    public Coordinate getTargetCoordinate(){
        return targetCoordinates;
    }

    public int getSpeed(){
        return speed;
    }
}
