package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;

public class UntilFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) {
        if (!(command instanceof Until untilCommand))
            throw new CloneCommandException("Command is not an Until");

        return new Until(untilCommand.getLabel(), untilCommand.getStartingLoopIndex(),
                untilCommand.getEndingLoopIndex(), untilCommand.getEnvironment());
    }
}
