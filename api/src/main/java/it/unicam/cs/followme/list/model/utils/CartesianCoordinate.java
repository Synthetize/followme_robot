package it.unicam.cs.followme.list.model.utils;


public record CartesianCoordinate(double xValue, double yValue) implements Coordinate {
    @Override
    public double getX() {
        return this.xValue;
    }

    @Override
    public double getY() {
        return this.yValue;
    }

    @Override
    public String toString() {
        return "CartesianCoordinate(" +
                "(" + xValue +
                " ," + yValue +
                ')';
    }
}
