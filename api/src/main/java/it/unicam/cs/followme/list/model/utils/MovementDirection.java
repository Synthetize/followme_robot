package it.unicam.cs.followme.list.model.utils;

import it.unicam.cs.followme.list.model.utils.Coordinate;

/**
 * Represents the direction of the last movement
 */
public interface MovementDirection {
    /**
     * @return the speed of the last movement
     */
    int  getSpeed();
    /**
     * @return a coordinate that represents the direction of the last movement
     */
    Coordinate getDirection();
}
