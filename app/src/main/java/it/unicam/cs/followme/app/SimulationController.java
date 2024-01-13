package it.unicam.cs.followme.app;

import it.unicam.cs.followme.list.ModelController;
import it.unicam.cs.followme.list.model.CartesianCoordinate;
import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.Environment;
import it.unicam.cs.followme.list.model.shapes.*;
import it.unicam.cs.followme.list.model.robots.BasicRobot;
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
import java.util.HashMap;
import java.util.Map;


public class SimulationController {
    private HashMap<BasicRobot, Coordinate> robotsCoordinates;
    private File shapesConfigFile;
    private File programFile;
    ModelController<BasicRobot> modelController = new ModelController<>();
    Environment<BasicRobot> environment;
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
        deltaTimeTextField.setText("1");
        simulationTimeTextField.setText("10");
    }

    private void addRobotsToGroup() {
        Map<BasicRobot, Coordinate> robots = environment.getRobotsDetails();
        robots.forEach((robot, coordinates) -> {
            Circle circle = new Circle(3, Color.BLACK);
            circle.setCenterX(coordinates.getX());
            circle.setCenterY(-1 * coordinates.getY());
            elementToShow.getChildren().add(circle);
        });
    }

    private void addShapesToGroup() {
        for (Map.Entry<Shape, Coordinate> entry : environment.getShapesDetails().entrySet()) {
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
    void runSimulation(MouseEvent event) {
        int deltaTime = Integer.parseInt(deltaTimeTextField.getText());
        int simulationTime = Integer.parseInt(simulationTimeTextField.getText());
        runButton.setDisable(true);
        modelController.runSimulation(deltaTime, simulationTime);
        elementToShow.getChildren().clear();
        addShapesToGroup();
        addRobotsToGroup();
    }

}
