package it.unicam.cs.followme.list.model;

/**
 * This interface is used to rappresent a generic shape
 *
 */
public interface Shape {
    /**
     * This method is used to calculate the area of the shape
     * @return the area of the shape
     */
    double getShapeArea();
    /**
     * This method return the condition label of the shape
     * @return the condition label of the shape
     */
    String getConditionLabel();
}
