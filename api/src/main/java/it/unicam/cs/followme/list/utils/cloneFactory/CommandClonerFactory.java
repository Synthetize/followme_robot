package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;

public interface CommandClonerFactory {
    /**
     * This method is used to create a clone of a command.
     * @param command the command to clone
     * @return the cloned command
     */
    Command cloneCommand(Command command);
}
