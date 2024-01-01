package it.unicam.cs.followme.list.parser_handler;

import it.unicam.cs.followme.list.executor.Simulator;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.commands.basic.*;
import it.unicam.cs.followme.list.model.commands.loops.LoopCommand;
import it.unicam.cs.followme.list.model.commands.loops.Repeat;
import it.unicam.cs.followme.list.model.commands.loops.Until;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.utils.CartesianCoordinate;
import it.unicam.cs.followme.list.model.utils.Coordinate;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.utilities.RobotCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ProgramParserHandler<R extends Robot> implements FollowMeParserHandler {

    private List<Command<R>> program;
    private final Simulator<R> executor;
    private final Environment<R> environment;
    private Stack<Integer> startingLoopIndexStack;

    public ProgramParserHandler(Environment<R> environment, Simulator<R> executor) {
        this.environment = environment;
        this.executor = executor;
    }

    @Override
    public void parsingStarted() {
        program = new ArrayList<>();
        startingLoopIndexStack = new Stack<>();
    }

    @Override
    public void parsingDone() {
        executor.setProgramList(program);
    }

    @Override
    public void moveCommand(double[] args) {
        Coordinate targetCoordinate = new CartesianCoordinate(args[0], args[1]);
        if(targetCoordinate.getX() == 0 && targetCoordinate.getY() == 0) {
            throw new IllegalArgumentException("The target coordinate cannot be (0,0)");
        }
        checkIfCoordinatesAreInRange(args[0], args[1]);
        validateSpeed(args[2]);
        Move<R> move = new Move<>(targetCoordinate, args[2], environment);
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
        Move<R> move = new Move<>(new CartesianCoordinate(xAvgValue, yAvgValue), args[4], environment);
        program.add(move);
    }

    @Override
    public void signalCommand(String label) {
        validateLabel(label);
        UpdateRobotLabel<R> updateRobotLabel = new UpdateRobotLabel<>(label, environment, RobotCommand.SIGNAL);
        program.add(updateRobotLabel);
    }

    private void validateLabel(String label) {
        if (!label.endsWith("_")) {
            throw new IllegalArgumentException("Label must end with \"_\"");
        }
    }

    @Override
    public void unsignalCommand(String label) {
        validateLabel(label);
        UpdateRobotLabel<R> updateRobotLabel = new UpdateRobotLabel<>(label, environment, RobotCommand.UNSIGNAL);
        program.add(updateRobotLabel);
    }

    @Override
    public void followCommand(String label, double[] args) {
        validateLabel(label);
        validateSpeed(args[1]);
        if (args[0] <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0");
        }
        Follow<R> follow = new Follow<>(label, args, environment);
        program.add(follow);
    }

    @Override
    public void stopCommand() {
        Stop<R> stop = new Stop<>();
        program.add(stop);
    }

    @Override
    public void continueCommand(int s) {
        if (s <= 0) {
            throw new IllegalArgumentException("Number of seconds must be greater than 0");
        }
        Continue<R> continueCommand = new Continue<>(s, environment);
        program.add(continueCommand);
    }

    @Override
    public void repeatCommandStart(int n) {
        if (n == 0 || n < -1) {
            throw new IllegalArgumentException("Number of repetitions must be greater than 0");
        }
        // the index of the loop is the size of the program list because the loopCommand is not yet added
        int loopStartIndex = program.size();
        Repeat<R> repeatCommand = new Repeat<>(n, loopStartIndex, -1, environment, program);
        program.add(repeatCommand);
        startingLoopIndexStack.push(loopStartIndex);
    }

    @Override
    public void untilCommandStart(String label) {
        validateLabel(label);
        // the index of the loop is the size of the program list because the loopCommand is not yet added
        int loopStartIndex = program.size();
        Until<R> untilCommand = new Until<>(label, loopStartIndex, -1, environment, program);
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
        Command<R> startingLoopCommand = program.get(startingLoopIndexStack.pop());
        if (!(startingLoopCommand instanceof LoopCommand<R> loopCommand)) {
            throw new IllegalArgumentException("The starting loop command must be a loop command");
        }
        loopCommand.setEndingLoopIndex(loopEndIndex);
        Done<R> doneCommand = new Done<>();
        program.add(doneCommand);
    }
}
