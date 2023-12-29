package it.unicam.cs.followme.list.model.commands.loops;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.List;

public class Repeat<R extends Robot> extends LoopCommand<R> {

    private final int repetitionNumbers;
    private final Environment<R> environment;


    public Repeat(int repetitionNumbers, int startingLoopIndex, int endingLoopIndex, Environment<R> environment, List<Command<R>> programList) {
        super(startingLoopIndex, endingLoopIndex, programList);
        this.repetitionNumbers = repetitionNumbers;
        this.environment = environment;
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
}
