package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class RepeatFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof Repeat repeatCommand))
            throw new CloneCommandException("Command is not a Repeat");
        return new Repeat(repeatCommand.getRepetitions(), repeatCommand.getStartingLoopIndex(), repeatCommand.getEndingLoopIndex());
    }
}
