package it.unicam.cs.followme.list;

import it.unicam.cs.followme.list.generators.DefaultShapesGenerator;
import it.unicam.cs.followme.list.generators.ShapesGenerator;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.parser_handler.ProgramParserHandler;
import it.unicam.cs.followme.list.simulator.RobotSimulator;
import it.unicam.cs.followme.list.simulator.Simulator;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.utilities.FollowMeShapeChecker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelController <R extends Robot> {
    Map<R, Coordinate> robotsHashMap;
    Map<Shape, Coordinate> shapesHashMap;
    List<Command<R>> program;
    Environment<R> environment;
    FollowMeParserHandler handler;
    FollowMeShapeChecker checker;
    ShapesGenerator shapesGenerator;


    Simulator<R> simulator;
    FollowMeParser parser;

    public void initialize() {
        robotsHashMap = new HashMap<>();
        shapesHashMap = new HashMap<>();
        environment = new SimulationEnvironment<>(shapesHashMap, robotsHashMap);

        program = new ArrayList<>();
        simulator = new RobotSimulator<>(program, new ArrayList<>(robotsHashMap.keySet()));

        handler = new ProgramParserHandler<>(environment, simulator);
        checker = FollowMeShapeChecker.DEFAULT_CHECKER;
        parser = new FollowMeParser(handler, checker);
        shapesGenerator = new DefaultShapesGenerator(parser);
    }

    public void setRobotsHashMap(Map<R, Coordinate> robotsHashMap) {
        this.robotsHashMap = robotsHashMap;
    }

    public void generateShapesFromFile(File shapesConfigFile) {
        this.shapesHashMap.putAll(shapesGenerator.generateShapes(shapesConfigFile));
    }

    public void generateCommandsFromFile(File programFile) throws FollowMeParserException, IOException {
        parser.parseRobotProgram(programFile);
    }
}
