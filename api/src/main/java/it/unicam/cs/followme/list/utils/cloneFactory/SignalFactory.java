package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Signal;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;

public class SignalFactory implements CommandClonerFactory {

    @Override
    public Command cloneCommand(Command command) {
        if (!(command instanceof Signal signalCommand))
            throw new CloneCommandException("Command is not a signal");
        return new Signal(signalCommand.getLabel(), signalCommand.getEnvironment());
    }
}
