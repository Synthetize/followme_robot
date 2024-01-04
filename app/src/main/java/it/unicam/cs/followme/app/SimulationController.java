package it.unicam.cs.followme.app;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {
    private HashMap<BasicRobot, Coordinate> robotsCoordinates;
    private File shapesConfigFile;
    private File programFile;
    ModelController<BasicRobot> modelController = new ModelController<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   protected void initializeEnvironment() {
        modelController.initialize();
        System.out.println("PATH SIMULATION CONTROLLER SHAPE CONFIG FILE");
        System.out.println(robotsCoordinates);
        System.out.println(shapesConfigFile.getPath());
        System.out.println(programFile.getPath());
        modelController.setRobotsHashMap(robotsCoordinates);
        //modelController.generateShapesFromFile(shapesConfigFile);
        try {
            modelController.generateCommandsFromFile(programFile);
        } catch (FollowMeParserException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setRobotsCoordinates(HashMap<BasicRobot, Coordinate> robotsCoordinates) {
        this.robotsCoordinates = robotsCoordinates;
    }

    protected void setShapesConfigFile(File shapesConfigFile) {
        this.shapesConfigFile = shapesConfigFile;
    }

    protected void setProgramFile(File programFile) {
        this.programFile = programFile;
    }

    @FXML
    private ScrollPane simulationArea;

}
