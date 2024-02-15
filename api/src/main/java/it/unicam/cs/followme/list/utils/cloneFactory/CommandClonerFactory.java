package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;

public interface CommandClonerFactory {
    Command cloneCommand(Command command);
}
