package it.unicam.cs.followme.list.parserHandler;

import it.unicam.cs.followme.list.simulator.Simulator;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ProgramParserHandler implements FollowMeParserHandler {

    private List<Command> program;
    private final Simulator simulator;
    private final Environment environment;
    private Stack<Integer> startingLoopIndexStack;

    public ProgramParserHandler(Environment environment, Simulator simulator) {
        this.environment = environment;
        this.simulator = simulator;
    }

    @Override
    public void parsingStarted() {
        program = new ArrayList<>();
        startingLoopIndexStack = new Stack<>();
    }

    @Override
    public void parsingDone() {
        simulator.setProgramList(program);
    }

    @Override
    public void moveCommand(double[] args) {
        Coordinate targetCoordinate = new CartesianCoordinate(args[0], args[1]);
        if(targetCoordinate.getX() == 0 && targetCoordinate.getY() == 0) {
            throw new IllegalArgumentException("The target coordinate cannot be (0,0)");
        }
        checkIfCoordinatesAreInRange(args[0], args[1]);
        validateSpeed(args[2]);
        Move move = new Move(targetCoordinate, args[2], environment);
        program.add(move);
    }

    private void checkIfCoordinatesAreInRange(double x, double y) {
        boolean isXCoordinateOutOfRange = x > 1 || x < -1;
        boolean isYCoordinateOutOfRange = y > 1 || y < -1;
        if (isXCoordinateOutOfRange || isYCoordinateOutOfRange) {
            throw new IllegalArgumentException("Coordinate must be between -1 and 1");
        }
    }

    private void validateSpeed(double speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed must be greater than 0");
        }
    }

    @Override
    public void moveRandomCommand(double[] args) {
        double xAvgValue = (args[0] + args[2]) / 2;
        double yAvgValue = (args[1] + args[3]) / 2;
        if (xAvgValue == 0 && yAvgValue == 0) {
            throw new IllegalArgumentException("The mean values of the coordinates cannot both be 0");
        }
        validateSpeed(args[4]);
        Move move = new Move(new CartesianCoordinate(xAvgValue, yAvgValue), args[4], environment);
        program.add(move);
    }

    @Override
    public void signalCommand(String label) {
        validateLabel(label);
        Signal signalCommand = new Signal(label, environment);
        program.add(signalCommand);
    }

    private void validateLabel(String label) {
        if (!label.endsWith("_")) {
            throw new IllegalArgumentException("Label must end with \"_\"");
        }
    }

    @Override
    public void unsignalCommand(String label) {
        validateLabel(label);
        Unsignal unsignalCommand = new Unsignal(label, environment);
        program.add(unsignalCommand);
    }

    @Override
    public void followCommand(String label, double[] args) {
        validateLabel(label);
        validateSpeed(args[1]);
        if (args[0] <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        Follow follow = new Follow(label, args, environment);
        program.add(follow);
    }

    @Override
    public void stopCommand() {
        Stop stop = new Stop();
        program.add(stop);
    }

    @Override
    public void continueCommand(int s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Number of seconds must be greater than 0");
        }
        Continue continueCommand = new Continue(s, environment);
        program.add(continueCommand);
    }

    @Override
    public void repeatCommandStart(int n) {
        if (n == 0 || n < -1) {
            throw new IllegalArgumentException("Number of repetitions must be greater than 0");
        }
        // the index of the loop is the size of the program list because the loopCommand is not yet added
        int loopStartIndex = program.size();
        Repeat repeatCommand = new Repeat(n, loopStartIndex, 0);
        program.add(repeatCommand);
        startingLoopIndexStack.push(loopStartIndex);
    }

    @Override
    public void untilCommandStart(String label) {
        validateLabel(label);
        // the index of the loop is the size of the program list because the loopCommand is not yet added
        int loopStartIndex = program.size();
        Until untilCommand = new Until(label, loopStartIndex, -1, environment);
        program.add(untilCommand);
        startingLoopIndexStack.push(loopStartIndex);
    }

    @Override
    public void doForeverStart() {
        //if the number of repetitions is -1 means that the loop is infinite
        repeatCommandStart(-1);
    }

    @Override
    public void doneCommand() {
        // the index of the end is the size of the program list because the doneCommand is not yet added
        int loopEndIndex = program.size();
        Command startingLoopCommand = program.get(startingLoopIndexStack.pop());
        if (!(startingLoopCommand instanceof LoopCommand loopCommand)) {
            throw new IllegalArgumentException("The starting loop command must be a loop command");
        }
        loopCommand.setEndingLoopIndex(loopEndIndex);
        Done doneCommand = new Done(loopCommand);
        program.add(doneCommand);
    }
}
