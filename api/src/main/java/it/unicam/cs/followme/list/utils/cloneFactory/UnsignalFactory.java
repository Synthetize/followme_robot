package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.UpdateRobotLabel;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class UnsignalFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof UpdateRobotLabel unsignalCommand))
            throw new CloneCommandException("Command is not an Unsignal");
        return new UpdateRobotLabel(unsignalCommand.getLabel(), unsignalCommand.getEnvironment(), unsignalCommand.getCommandType());
    }
}
