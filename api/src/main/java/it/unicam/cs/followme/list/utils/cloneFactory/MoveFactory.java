package it.unicam.cs.followme.list.utils.cloneFactory;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Move;
import it.unicam.cs.followme.list.utils.CloneCommandException;

public class MoveFactory implements CommandClonerFactory {
    @Override
    public Command cloneCommand(Command command) throws CloneCommandException {
        if (!(command instanceof Move moveCommand))
            throw new CloneCommandException("Command is not a Move");
        return new Move(moveCommand.getCoordinate(), moveCommand.getSpeed(), moveCommand.getEnvironment());
    }
}
