package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.UpdateRobotLabel;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class SignalFactory implements CommandClonerFactory {

    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof UpdateRobotLabel signalCommand))
            throw new CloneCommandException("Command is not a signal");
        return new UpdateRobotLabel(signalCommand.getLabel(), signalCommand.getEnvironment(), signalCommand.getCommandType());
    }
}
