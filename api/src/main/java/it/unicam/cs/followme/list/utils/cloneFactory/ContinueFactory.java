package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Continue;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class ContinueFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof Continue continueCommand))
            throw new CloneCommandException("Command is not a Continue");
        return new Continue(continueCommand.getSeconds(), continueCommand.getEnvironment());
    }
}
