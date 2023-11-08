package it.unicam.cs.followme.list.model.utils.commands;

import it.unicam.cs.followme.utilities.RobotCommand;

public class ConditionStatusCommand implements Command {
    private final RobotCommand commandType;
    private final String label;

    public ConditionStatusCommand(RobotCommand commandType , String label) {
        this.commandType = commandType;
        this.label = label;
    }

    @Override
    public RobotCommand getCommandType() {
        return commandType;
    }

    public String getLabel(){
        return label;
    }
}
