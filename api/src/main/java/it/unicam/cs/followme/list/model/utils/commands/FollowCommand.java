package it.unicam.cs.followme.list.model.utils.commands;

import it.unicam.cs.followme.utilities.RobotCommand;

public class FollowCommand implements Command {
    private final RobotCommand commandType;
    private final String signal;
    private final int distance;
    private final int speed;

    public FollowCommand(String label, double[] args) {
        this.commandType = RobotCommand.FOLLOW;
        this.signal = label;
        this.distance = (int) args[0];
        this.speed = (int) args[1];
    }

    @Override
    public RobotCommand getCommandType() {
        return commandType;
    }

    public String getLabel(){
        return signal;
    }

    public int getDistance(){
        return distance;
    }

    public int getSpeed(){
        return speed;
    }
}
