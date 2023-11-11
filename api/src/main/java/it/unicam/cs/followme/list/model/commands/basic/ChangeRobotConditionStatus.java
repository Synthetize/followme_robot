package it.unicam.cs.followme.list.model.commands.basic;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

public class ChangeRobotConditionStatus implements Command {
    private final RobotCommand commandType;
    private final String label;

    public ChangeRobotConditionStatus(RobotCommand commandType , String label) {
        this.commandType = commandType;
        this.label = label;
    }

    @Override
    public RobotCommand getCommandType() {
        return commandType;
    }

    @Override
    public void Run(Robot robot) {

    }

    public String getLabel(){
        return label;
    }
}
