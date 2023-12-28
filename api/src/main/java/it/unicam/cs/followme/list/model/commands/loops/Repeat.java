package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.Done;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Repeat<R extends Robot> extends LoopCommand<R> {

    private final int repetitionNumbers;
    private final Environment<R> environment;
    private final List<Command<R>> programList;

    public Repeat(int repetitionNumbers, int startingLoopIndex, int endingLoopIndex, Environment<R> environment, List<Command<R>> programList) {
        super(startingLoopIndex, endingLoopIndex);
        this.repetitionNumbers = repetitionNumbers;
        this.environment = environment;
        this.programList = programList;
    }

    @Override
    public RobotCommand getCommandType() {
        if (repetitionNumbers == -1) return RobotCommand.FOREVER;
        return RobotCommand.REPEAT;
    }

    @Override
    public void run(R robot, double delta_t) {
        if (repetitionNumbers == -1) {
            while (true)
                executeCommand(robot, delta_t);
        } else {
            for (int i = 0; i < repetitionNumbers; i++)
                executeCommand(robot, delta_t);
        }
    }

    private void executeCommand(R robot, double delta_t) {
        int currentCommandIndex = getStartingLoopIndex() + 1;
        while (currentCommandIndex < getEndingLoopIndex()) {
            if (programList.get(currentCommandIndex) instanceof LoopCommand) {
                programList.get(currentCommandIndex).run(robot, delta_t);
                currentCommandIndex = ((LoopCommand<R>) programList.get(currentCommandIndex)).getEndingLoopIndex();
            } else {
                programList.get(currentCommandIndex).run(robot, delta_t);
            }
            currentCommandIndex++;
        }
    }
}
