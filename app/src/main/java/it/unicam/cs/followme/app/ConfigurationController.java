package it.unicam.cs.followme.app;

import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConfigurationController implements Initializable {

    FileChooser fileChooser = new FileChooser();
    List<Coordinate> robotsCoordinates = new ArrayList<>();
    File shapesConfigFile;
    File programFile;
    Alert alert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("app/src/main/resources/configuration_files"));
        showRobotsArea.setEditable(false);
    }

    @FXML
    private TextArea showRobotsArea;

    @FXML
    private TextField xCoordinate;

    @FXML
    private TextField yCoordinate;
    @FXML
    void getShapesFile(MouseEvent event) {
       shapesConfigFile = fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    void getProgram(MouseEvent event) {
        programFile = fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    void addRobot(MouseEvent event) {
        if(xCoordinate.getText().isBlank() || yCoordinate.getText().isBlank() || !xCoordinate.getText().matches("-?\\d+(\\.\\d+)?") || !yCoordinate.getText().matches("-?\\d+(\\.\\d+)?")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid coordinate");
            alert.setContentText("Please insert a valid coordinate");
            alert.showAndWait();
        }
        robotsCoordinates.add(new CartesianCoordinate(Double.parseDouble(xCoordinate.getText()), Double.parseDouble(yCoordinate.getText())));

        showRobotsArea.appendText("Robot " + robotsCoordinates.size() + ": " + robotsCoordinates.get(robotsCoordinates.size() - 1).toString() + "\n");
    }

    @FXML
    void openSimulationScene(MouseEvent event) {
        if(shapesConfigFile == null || programFile == null || robotsCoordinates.isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing configuration");
            alert.setContentText("Please insert all the required configuration files");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("simulation.fxml"));
                Parent root = loader.load();
                SimulationController simulationController = loader.getController();
                simulationController.setRobotsCoordinates(robotsCoordinates);
                simulationController.setShapesConfigFile(shapesConfigFile);
                simulationController.setProgramFile(programFile);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected File getShapesConfigFile() {
        return shapesConfigFile;
    }

    protected File getProgramFile() {
        return programFile;
    }

    protected List<Coordinate> getRobotsCoordinates() {
        return robotsCoordinates;
    }

}
