package it.unicam.cs.followme.list.model;

/**
 * This interface is used to rappresent a generic coordinate
 */
public interface Coordinate<T extends Coordinate<T>> {
    /**
     * This method is used to calculate the distance between two coordinates
     * @param coordinates the coordinates to calculate the distance
     * @return the distance between the two coordinates
     */
    double calculateDistance(T coordinates);
}
