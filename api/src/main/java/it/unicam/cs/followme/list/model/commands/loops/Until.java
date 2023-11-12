package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class Until extends LoopCommand {
    private final RobotCommand commandType;
    private final String label;
    private int endingLoopIndex;
    public Until(RobotCommand commandType , String label) {
        this.commandType = commandType;
        this.label = label;
    }
    @Override
    public RobotCommand getCommandType() {
        return this.commandType;
    }

    @Override
    public void run(Robot robot) {

    }

    public String getLabel(){
        return this.label;
    }
}
