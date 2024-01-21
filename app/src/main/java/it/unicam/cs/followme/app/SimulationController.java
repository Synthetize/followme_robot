package it.unicam.cs.followme.app;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.robots.Robot;
import it.unicam.cs.followme.list.model.shapes.*;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class SimulationController {
    private HashMap<Robot, Coordinate> robotsCoordinates;
    private File shapesConfigFile;
    private File programFile;
    ModelController modelController = new ModelController();
    Environment environment;
    Group elementToShow;


    @FXML
    private TextField deltaTimeTextField;
    @FXML
    private TextField simulationTimeTextField;
    @FXML
    private ScrollPane simulationArea;
    @FXML
    private TextArea simulationLog;
    @FXML
    private Button runButton;


    public void initializeEnvironment() {
        modelController.initialize();
        modelController.setRobotsHashMap(robotsCoordinates);
        modelController.generateShapesFromFile(shapesConfigFile);
        try {
            modelController.generateCommandsFromFile(programFile);
        } catch (FollowMeParserException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeSimulationArea() {
        environment = modelController.getEnvironment();
        elementToShow = new Group();
        addShapesToGroup();
        addRobotsToGroup();
        simulationArea.setContent(elementToShow);
        simulationArea.setStyle("-fx-background: rgba(110,110,110,0.06);");
        simulationArea.setHvalue(0.5);
        simulationArea.setVvalue(0.5);
        deltaTimeTextField.setText("1");
        simulationTimeTextField.setText("10");
        simulationLog.setEditable(false);
    }

    private void addRobotsToGroup() {
        Map<Robot, Coordinate> robots = environment.robotsDetails();
        robots.forEach((robot, coordinates) -> {
            Circle circle = new Circle(3, Color.BLACK);
            circle.setCenterX(coordinates.getX());
            circle.setCenterY(-1 * coordinates.getY());
            elementToShow.getChildren().add(circle);
        });
    }

    private void addShapesToGroup() {
        for (Map.Entry<Shape, Coordinate> entry : environment.shapesDetails().entrySet()) {
            if (entry.getKey() instanceof RectangleShape) {
                addRectangleToPane(entry);
            } else {
                addCircleToPane(entry);
            }
        }
    }

    private void addCircleToPane(Map.Entry<Shape, Coordinate> entry) {
        CircleShape circleShape = (CircleShape) entry.getKey();
        Circle circle = new Circle(circleShape.getRadius(), Color.RED);
        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) entry.getValue();
        circle.setCenterX(cartesianCoordinate.getX());
        circle.setCenterY(-1 * cartesianCoordinate.getY());
        circle.setOpacity(0.5);
        elementToShow.getChildren().add(circle);
    }

    private void addRectangleToPane(Map.Entry<Shape, Coordinate> entry) {
        RectangleShape rectangleShape = (RectangleShape) entry.getKey();
        CartesianCoordinate coordinate = (CartesianCoordinate) entry.getValue();
        double xTopLeftAngle = coordinate.getX() - (rectangleShape.width() / 2);              //top left x
        double yTopLeftAngle = (coordinate.getY() * -1) - (rectangleShape.height() / 2);      //top left y
        Rectangle rectangle = new Rectangle(xTopLeftAngle, yTopLeftAngle, rectangleShape.width(), rectangleShape.height());
        rectangle.setFill(Color.BLUE);
        rectangle.setOpacity(0.5);
        elementToShow.getChildren().add(rectangle);
    }

    protected void setRobotsCoordinates(HashMap<Robot, Coordinate> robotsCoordinates) {
        this.robotsCoordinates = robotsCoordinates;
    }

    protected void setShapesConfigFile(File shapesConfigFile) {
        this.shapesConfigFile = shapesConfigFile;
    }

    protected void setProgramFile(File programFile) {
        this.programFile = programFile;
    }

    @FXML
    void runSimulation(MouseEvent event) {
        simulationTimeTextField.setDisable(true);
        deltaTimeTextField.setDisable(true);
        int deltaTime = Integer.parseInt(deltaTimeTextField.getText());
        int simulationTime = Integer.parseInt(simulationTimeTextField.getText());
        modelController.runSimulation(deltaTime, simulationTime);
        elementToShow.getChildren().clear();
        addShapesToGroup();
        addRobotsToGroup();
        readSimulationLog();
        simulationArea.setHvalue(0.5);
        simulationArea.setVvalue(0.5);
    }

    private void readSimulationLog() {
        String filePath = "configuration_files/log.txt";
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            simulationLog.clear();
            simulationLog.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private long logFilePosition = 0;

//    private void readSimulationLog() {
//        String filePath = "configuration_files/log.txt";
//        try {
//            RandomAccessFile logFile = new RandomAccessFile(filePath, "r");
//            logFile.seek(logFilePosition);
//            String line;
//            StringBuilder newLogContent = new StringBuilder();
//            while ((line = logFile.readLine()) != null) {
//                newLogContent.append(line).append("\n");
//            }
//            logFilePosition = logFile.getFilePointer();
//
//            simulationLog.clear();
//            simulationLog.appendText(newLogContent.toString());
//            logFile.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
