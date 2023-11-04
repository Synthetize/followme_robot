package it.unicam.cs.followme.list.model;

public class RectangleShape implements Shape {
    private final double width;
    private final double height;
    private final double area;
    private final String conditionLabel;

    public RectangleShape(double width, double height, String conditionLabel) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be greater than 0");
        if (!(conditionLabel.endsWith("_")))
            throw new IllegalArgumentException("Condition label must end with _");
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
}
