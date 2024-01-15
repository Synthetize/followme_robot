package it.unicam.cs.followme.list.model.robots;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;

import java.util.List;

/**
 * This interface is used to represent a generic robot
 */
public interface Robot {
    /**
     * Returns the current labels associated with the robot.
     *
     * @return The list of current labels.
     */
    List<String> getCurrentConditionLabels();

    /**
     * Adds a label to the robot.
     *
     * @param label The label to be added.
     */
    void addLabel(String label);

    /**
     * Removes a label from the robot.
     *
     * @param label The label to be removed.
     */
    void removeLabel(String label);

    /**
     * Gets the value of the last movement of the robot.
     *
     * @return a coordinate representing x and y values of the last movement.
     */
    Coordinate getLastMovementValues();

    /**
     * Sets x and y values of the last movement of the robot.
     *
     * @param movement The movement of the robot.
     */
    void setLastMovementValues(Coordinate movement);

    /**
     * Clears the current labels of the robot.
     */
    void clearCurrentConditionLabels();

    /**
     * Sets the program of the robot.
     *
     * @param commands The program of the robot.
     */
    void setProgram(List<Command> commands);

    /**
     * Returns the program of the robot.
     */
    List<Command> getProgram();

    /**
     * Return the index of the current command that the robot will execute.
     */
    int getCurrentCommandIndex();

    /**
     * Returns the command that the robot will execute.
     */
    void setCurrentCommandIndex(int index);

    /**
     * Increments the index of the current command that the robot will execute.
     */
    void incrementCurrentCommandIndex();

}
