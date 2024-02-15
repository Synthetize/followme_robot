package it.unicam.cs.followme.list.utils;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.utils.cloneFactory.*;
import it.unicam.cs.followme.list.utils.exceptions.InvalidFactoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class ProgramCloner {
    private final Map<Class<? extends Command>, CommandClonerFactory> factoryMap;
    private final List<Command> clonedProgram = new ArrayList<>();

    public ProgramCloner(Map<Class<? extends Command>, CommandClonerFactory> factoryMap) {
        this.factoryMap = factoryMap;
        factoryMap.put(Continue.class, new ContinueFactory());
        factoryMap.put(Done.class, new DoneFactory(this.clonedProgram));
        factoryMap.put(Follow.class, new FollowFactory());
        factoryMap.put(Move.class, new MoveFactory());
        factoryMap.put(Repeat.class, new RepeatFactory());
        factoryMap.put(Signal.class, new SignalFactory());
        factoryMap.put(Stop.class, new StopFactory());
        factoryMap.put(Unsignal.class, new UnsignalFactory());
        factoryMap.put(Until.class, new UntilFactory());
    }

    public List<Command> clone(List<Command> program) {
        for (Command command : program) {
            CommandClonerFactory factory = factoryMap.get(command.getClass());
            if (factory == null)
                throw new InvalidFactoryException("No factory found for " + command.getClass().getSimpleName());
            clonedProgram.add(factory.cloneCommand(command));
        }
        return clonedProgram;
    }

}
