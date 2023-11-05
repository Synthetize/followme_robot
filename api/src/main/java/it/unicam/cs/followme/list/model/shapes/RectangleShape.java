package it.unicam.cs.followme.list.model.shapes;

public record RectangleShape(double width, double height, String conditionLabel) implements Shape {
    public RectangleShape {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be greater than 0");
        if (!(conditionLabel.endsWith("_")))
            throw new IllegalArgumentException("Condition label must end with _");
    }

    @Override
    public String getConditionLabel() {
        return this.conditionLabel;
    }
}
