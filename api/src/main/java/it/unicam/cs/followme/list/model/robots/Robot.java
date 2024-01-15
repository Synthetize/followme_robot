package it.unicam.cs.followme.list.model.robots;

import it.unicam.cs.followme.list.model.Coordinate;
import it.unicam.cs.followme.list.model.commands.Command;

import java.util.List;

/**
 * This interface is used to rappresent a generic robot
 */
public interface Robot {
    /**
     * Returns the current labels associated with the robot.
     * @return The list of current labels.
     */
    List<String> getCurrentConditionLabels();
    /**
     * Adds a label to the robot.
     * @param label The label to be added.
     */
    void addLabel(String label);
    /**
     * Removes a label from the robot.
     * @param label The label to be removed.
     */
    void removeLabel(String label);
    /**
     * Gets the current direction of the robot.
     * @return The direction of the robot.
     */
    Coordinate getLastMovementDirection();
    /**
     * Sets the direction and speed of the robot.
     * @param direction The direction of the robot.
     */
    void setLastMovementDirection(Coordinate direction);
    /**
     * Clears the current labels of the robot.
     */
    void clearCurrentConditionLabels();

    void setProgram(List<Command> commands);

}
