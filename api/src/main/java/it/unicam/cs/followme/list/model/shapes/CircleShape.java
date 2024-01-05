package it.unicam.cs.followme.list.model.shapes;

public record CircleShape(double radius, String conditionLabel) implements Shape {
    public CircleShape {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be greater than 0");
        if (!(conditionLabel.endsWith("_")))
            throw new IllegalArgumentException("Condition label must end with _");
    }
    @Override
    public String getConditionLabel() {
        return this.conditionLabel;
    }

    public double getRadius() {
        return this.radius;
    }
}
