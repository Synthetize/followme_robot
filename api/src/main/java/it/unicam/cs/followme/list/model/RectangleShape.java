package it.unicam.cs.followme.list.model;

public class RectangleShape implements Shape {
    private double width;
    private double height;
    private double area;
    private String conditionLabel;

    public RectangleShape(double width, double height, String conditionLabel) {
        if (width <= 0 || height <= 0)
            throw new ArithmeticException("Width and height must be greater than 0");
        if (!(conditionLabel.startsWith("_")))
            throw new IllegalArgumentException("Condition label must start with _");
        this.conditionLabel = conditionLabel;
        this.width = width;
        this.height = height;
        this.area = calculateArea();
    }

    private double calculateArea() {
        return this.width * this.height;
    }

    @Override
    public double getShapeArea() {
        return this.area;
    }

    @Override
    public String getConditionLabel() {
        return this.conditionLabel;
    }

    @Override
    public boolean hasTheRobotInside(Coordinate coordinates) {
        return false;
    }
}
