package it.unicam.cs.followme.list;

import it.unicam.cs.followme.list.generators.DefaultShapesGenerator;
import it.unicam.cs.followme.list.generators.ShapesGenerator;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.SimulationEnvironment;
import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.Shape;
import it.unicam.cs.followme.list.parserHandler.ProgramParserHandler;
import it.unicam.cs.followme.list.simulator.RobotSimulator;
import it.unicam.cs.followme.list.simulator.Simulator;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.utilities.FollowMeShapeChecker;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class ModelController {
    private Map<Robot, Coordinate> robotsHashMap;
    private Map<Shape, Coordinate> shapesHashMap;
    private Environment environment;
    private ShapesGenerator shapesGenerator;
    private Simulator simulator;
    private FollowMeParser parser;
    public static final Logger LOGGER = Logger.getLogger(ModelController.class.getName());

    public void initialize() {
        loggerSetup();
        robotsHashMap = new HashMap<>();
        shapesHashMap = new HashMap<>();
        environment = new SimulationEnvironment(shapesHashMap, robotsHashMap);

        List<Command> program = new ArrayList<>();
        simulator = new RobotSimulator(program, robotsHashMap);

        FollowMeParserHandler handler = new ProgramParserHandler(environment, simulator);
        FollowMeShapeChecker checker = FollowMeShapeChecker.DEFAULT_CHECKER;
        parser = new FollowMeParser(handler, checker);
        shapesGenerator = new DefaultShapesGenerator(parser);
        LOGGER.info("ModelController initialized");
    }

    private void loggerSetup() {
        try {
            FileHandler fileHandler = new FileHandler("configuration_files/log.txt");
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(java.util.logging.Level.ALL);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.info("LoggerHandler initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRobotsHashMap(Map<Robot, Coordinate> robotsHashMap) {
        this.robotsHashMap.clear();
        this.robotsHashMap.putAll(robotsHashMap);
    }

    public void generateShapesFromFile(File shapesConfigFile) {
        this.shapesHashMap.putAll(shapesGenerator.generateShapes(shapesConfigFile));
    }

    public void generateCommandsFromFile(File programFile) throws FollowMeParserException, IOException {
        parser.parseRobotProgram(programFile);
        simulator.setup();
    }

    public void runSimulation(double delta_t, double time) {
        simulator.simulate(delta_t, time);
    }

    public Environment getEnvironment() {
        return environment;
    }

}
