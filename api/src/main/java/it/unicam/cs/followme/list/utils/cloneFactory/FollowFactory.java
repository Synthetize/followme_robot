package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Follow;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class FollowFactory implements CommandClonerFactory {

    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof Follow followCommand))
            throw new CloneCommandException("Command is not a Follow");
        return new Follow(followCommand.getLabel(), followCommand.getArgs(), followCommand.getEnvironment());
    }
}
