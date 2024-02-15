package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.utils.exceptions.CloneCommandException;

import java.util.List;

public class DoneFactory implements CommandClonerFactory {

    private final List<Command> clonedProgram;

    public DoneFactory(List<Command> clonedProgram) {
        this.clonedProgram = clonedProgram;
    }
    @Override
    public Command cloneCommand(Command command) {
        if (!(command instanceof Done doneCommand))
            throw new CloneCommandException("Command is not a Done");
        int startingLoopIndex = doneCommand.startingLoopCommand().getStartingLoopIndex();
        return new Done((LoopCommand) clonedProgram.get(startingLoopIndex));
    }
}
