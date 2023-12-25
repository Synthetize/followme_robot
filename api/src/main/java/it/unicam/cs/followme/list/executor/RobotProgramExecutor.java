package it.unicam.cs.followme.list.executor;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;

public class RobotProgramExecutor<R extends Robot> implements ProgramExecutor<R>{
    private List<Command<R>> programList;
    public RobotProgramExecutor(List<Command<R>> programList) {
        this.programList = programList;
    }
    @Override
    public void setProgramList(List<Command<R>> programList) {
        this.programList.addAll(programList);
    }
    @Override
    public void executeProgram(Robot robot) {

    }
}
