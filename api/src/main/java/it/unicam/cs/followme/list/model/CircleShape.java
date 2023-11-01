package it.unicam.cs.followme.list.model;

public class CircleShape implements Shape{
    private double radius;
    private double area;
    private String conditionLabel;


    public CircleShape(double radius, String conditionLabel) {
        if(radius <= 0)
            throw new IllegalArgumentException("Radius must be greater than 0");
        if(!(conditionLabel.startsWith("_")))
            throw new IllegalArgumentException("Condition label must start with _");
        this.conditionLabel = conditionLabel;
        this.radius = radius;
        this.area = calculateArea();
    }
    private double calculateArea(){
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public double getShapeArea() {
        return this.area;
    }

    @Override
    public String getConditionLabel() {
        return this.conditionLabel;
    }

    /*@Override
    public boolean hasTheRobotInside(Coordinate coordinates) {
        return false;
    }*/
}
