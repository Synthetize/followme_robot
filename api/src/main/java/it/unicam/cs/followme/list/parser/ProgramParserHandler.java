package it.unicam.cs.followme.list.parser;
import it.unicam.cs.followme.list.executor.ProgramExecutor;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;

import java.util.List;

public class ProgramParserHandler<R extends Robot> implements  FollowMeParserHandler {

    private final List<Command> program;
    private final ProgramExecutor executor;
    private final Environment<R> environment;

    public ProgramParserHandler(Environment<R> environment, ProgramExecutor executor, List<Command> program) {
        this.environment = environment;
        this.executor = executor;
        this.program = program;
    }

    @Override
    public void parsingStarted() {
        program.clear();
    }

    @Override
    public void parsingDone() {
        //chiama il metodo che esegue il programma
    }

    @Override
    public void moveCommand(double[] args) {
    }

    @Override
    public void moveRandomCommand(double[] args) {

    }

    @Override
    public void signalCommand(String label) {

    }

    @Override
    public void unsignalCommand(String label) {

    }

    @Override
    public void followCommand(String label, double[] args) {

    }

    @Override
    public void stopCommand() {

    }

    @Override
    public void continueCommand(int s) {

    }

    @Override
    public void repeatCommandStart(int n) {

    }

    @Override
    public void untilCommandStart(String label) {

    }

    @Override
    public void doForeverStart() {

    }

    @Override
    public void doneCommand() {

    }
}
