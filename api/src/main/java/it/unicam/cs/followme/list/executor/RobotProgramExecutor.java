package it.unicam.cs.followme.list.executor;

import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;

public class RobotProgramExecutor<R extends Robot> implements ProgramExecutor<R> {
    private List<Command<R>> programList;
    private int currentCommandIndex = 0;

    public RobotProgramExecutor(List<Command<R>> programList/*, R robot, Environment<R> environment*/) {
        this.programList = programList;
    }

    @Override
    public void setProgramList(List<Command<R>> programList) {
        this.programList.addAll(programList);
    }

    @Override
    public void executeProgram(Robot robot) {
        if (currentCommandIndex >= programList.size()) {
            stopExecution();
        } else if (findLoop()) {
            executeLoop();
        } else {
            executeCommand();
        }
    }

    private boolean findLoop() {
        return false;
    }

    private void executeCommand() {
    }

    private void executeLoop() {


    }


    private void stopExecution() {

    }
}
