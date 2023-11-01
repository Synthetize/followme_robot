package it.unicam.cs.followme.list.model;

import java.security.PublicKey;

public class CartesianCoordinate implements Coordinate<CartesianCoordinate>{
    private double x;
    private double y;

    public CartesianCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
    @Override
    public double calculateDistance(CartesianCoordinate coordinates) {
        return Math.sqrt(Math.pow(coordinates.getX() - this.x, 2) + Math.pow(coordinates.getY() - this.y, 2));
    }
}
