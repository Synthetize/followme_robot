package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Stop;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class StopFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof Stop))
            throw new CloneCommandException("Command is not a Stop");
        return new Stop();
    }
}
