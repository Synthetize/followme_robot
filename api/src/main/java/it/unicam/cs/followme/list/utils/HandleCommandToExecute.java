package it.unicam.cs.followme.list.utils;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HandleCommandToExecute<R extends Robot> extends SimulationTimer {


    private List<Command<R>> programList;
    private AtomicInteger currentCommandIndex;

    static private CommandExecutionListener commandExecutionListener;

    public HandleCommandToExecute(AtomicInteger startingLoopIndex, List<Command<R>> programList) {
        this.currentCommandIndex = startingLoopIndex;
        this.programList = programList;
    }
    /**
     * This method is used to find and execute a loop or basic command from the program list.
     * If the command is an instance of LoopCommand, it runs the command and sets the current command index to the ending index of the loop.
     * If the command is not a LoopCommand, it simply runs the command.
     * After executing a command, it increments the current command index.
     *
     * @param delta_t The time step for the simulation.
     * @param robot The robot that the command will be executed on.
     * @param endingIndex The index at which the execution of the loop or the program should stop.
     */
    public void findLoopOrBasicCommandAndCallRun(double delta_t, R robot, int endingIndex) {
        if(incrementTimerIfNotOver()) {
            //stop execution of the loop or the program based on the ending index
            currentCommandIndex.set(endingIndex);
            return;
        }
        if (programList.get(currentCommandIndex.get()) instanceof LoopCommand<R> loopCommand) {
            programList.get(currentCommandIndex.get()).run(robot, delta_t);
            currentCommandIndex.set(loopCommand.getEndingLoopIndex());
        } else {
            programList.get(currentCommandIndex.get()).run(robot, delta_t);
        }
        currentCommandIndex.incrementAndGet();
        if (commandExecutionListener != null) {
            commandExecutionListener.onCommandExecution();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public void setCommandExecutionListener(CommandExecutionListener commandExecutionListener) {
        HandleCommandToExecute.commandExecutionListener = commandExecutionListener;
    }
}
