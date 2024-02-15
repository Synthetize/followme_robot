package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Unsignal;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;

public class UnsignalFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) {
        if (!(command instanceof Unsignal unsignalCommand))
            throw new CloneCommandException("Command is not an Unsignal");
        return new Unsignal(unsignalCommand.getLabel(), unsignalCommand.getEnvironment());
    }
}
