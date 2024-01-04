package it.unicam.cs.followme.app;

import it.unicam.cs.followme.list.model.Coordinate;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController implements Initializable{
    private List<Coordinate> robotsCoordinates;
    private File shapesConfigFile;
    private File programFile;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("SimulationController initialized");

    }

    protected void setRobotsCoordinates(List<Coordinate> robotsCoordinates){
        this.robotsCoordinates = robotsCoordinates;
    }

    protected void setShapesConfigFile(File shapesConfigFile){
        this.shapesConfigFile = shapesConfigFile;
    }

    protected void setProgramFile(File programFile){
        this.programFile = programFile;
    }


}
