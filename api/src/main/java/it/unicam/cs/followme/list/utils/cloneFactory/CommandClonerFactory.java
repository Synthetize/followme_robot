package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public interface CommandClonerFactory {
    Command cloneCommand(Command command) throws CloneCommandException;
}
