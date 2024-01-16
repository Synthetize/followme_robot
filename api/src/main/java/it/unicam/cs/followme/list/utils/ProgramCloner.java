package it.unicam.cs.followme.list.utils;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a static method to clone a program.
 * This is useful to avoid side effects when a program is executed, since the counter of some commands may be
 * modified during the execution.
 */
public class ProgramCloner {
    public static List<Command> clone(List<Command> program) {
        List<Command> clonedProgram = new ArrayList<>();
        for (Command command : program) {
            switch (command.getCommandType()) {
                case CONTINUE:
                    cloneContinue(command, clonedProgram);
                    break;
                case DONE:
                    cloneDone(command, clonedProgram);
                    break;
                case FOLLOW:
                    cloneFollow(command, clonedProgram);
                    break;
                case MOVE:
                    cloneMove(command, clonedProgram);
                    break;
                case STOP:
                    cloneStop(command, clonedProgram);
                    break;
                case SIGNAL:
                    cloneSignal(command, clonedProgram);
                    break;
                case UNSIGNAL:
                    cloneUnsignal(command, clonedProgram);
                    break;
                case REPEAT:
                    cloneRepeat(command, clonedProgram);
                    break;
                case UNTIL:
                    cloneUntil(command, clonedProgram);
                    break;
                default:
                    throw new IllegalArgumentException("Command type not recognized");
            }
        }
        return clonedProgram;
    }

    private static void cloneContinue(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Continue continueCommand))
            throw new IllegalArgumentException("Continue not recognized");
        clonedProgram.add(new Continue(continueCommand.getSeconds(), continueCommand.getEnvironment()));
    }

    private static void cloneDone(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Done doneCommand))
            throw new IllegalArgumentException("Done not recognized");
        int startingLoopIndex = doneCommand.startingLoopCommand().getStartingLoopIndex();
        clonedProgram.add(new Done((LoopCommand) clonedProgram.get(startingLoopIndex)));
    }

    private static void cloneFollow(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Follow followCommand))
            throw new IllegalArgumentException("Follow not recognized");
        String label = followCommand.getLabel();
        double[] args = followCommand.getArgs();
        Environment environment = followCommand.getEnvironment();
        clonedProgram.add(new Follow(label, args, environment));
    }

    private static void cloneMove(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Move moveCommand))
            throw new IllegalArgumentException("Move not recognized");
        Coordinate coordinate = moveCommand.getCoordinate();
        double speed = moveCommand.getSpeed();
        Environment moveEnvironment = moveCommand.getEnvironment();
        clonedProgram.add(new Move(coordinate, speed, moveEnvironment));

    }

    private static void cloneStop(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Stop))
            throw new IllegalArgumentException("Stop not recognized");
        clonedProgram.add(new Stop());
    }

    private static void cloneSignal(Command command, List<Command> clonedProgram) {
        if (!(command instanceof UpdateRobotLabel signalCommand))
            throw new IllegalArgumentException("Signal not recognized");
        String signal = signalCommand.getLabel();
        Environment signalEnvironment = signalCommand.getEnvironment();
        clonedProgram.add(new UpdateRobotLabel(signal, signalEnvironment, signalCommand.getCommandType()));
    }

    private static void cloneUnsignal(Command command, List<Command> clonedProgram) {
        if (!(command instanceof UpdateRobotLabel unsignalCommand))
            throw new IllegalArgumentException("Unsignal not recognized");
        String unsignal = unsignalCommand.getLabel();
        Environment unsignalEnvironment = unsignalCommand.getEnvironment();
        clonedProgram.add(new UpdateRobotLabel(unsignal, unsignalEnvironment, unsignalCommand.getCommandType()));
    }

    private static void cloneRepeat(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Repeat repeatCommand))
            throw new IllegalArgumentException("Repeat not recognized");
        int times = repeatCommand.getRepetitions();
        int startingIndex = repeatCommand.getStartingIndex();
        int endingIndex = repeatCommand.getEndingIndex();
        clonedProgram.add(new Repeat(times, startingIndex, endingIndex));
    }

    private static void cloneUntil(Command command, List<Command> clonedProgram) {
        if (!(command instanceof Until untilCommand))
            throw new IllegalArgumentException("Until not recognized");
        String untilLabel = untilCommand.getLabel();
        int untilStartingIndex = untilCommand.getStartingLoopIndex();
        int untilEndingIndex = untilCommand.getEndingLoopIndex();
        Environment untilEnvironment = untilCommand.getEnvironment();
        clonedProgram.add(new Until(untilLabel, untilStartingIndex, untilEndingIndex, untilEnvironment));
    }


}
