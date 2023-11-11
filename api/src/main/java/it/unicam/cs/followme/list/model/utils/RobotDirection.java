package it.unicam.cs.followme.list.model.utils;

public class RobotDirection implements MovementDirection {

    private final Coordinate direction;
    private final int speed;

    // TODO: 05/11/2023 passare coordinate invece di differenceX e differenceY, rimuovere speed
    // TODO: 05/11/2023 invece di ritornare una coordinata ritorna due valori 'movementX' e 'movementY'
    public RobotDirection(double differenceX, double differenceY, int speed) {
        if (speed < 0)
            throw new IllegalArgumentException("Speed must be positive");
        double direction = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
        this.speed = speed;
        double xMovementValue = (differenceX / direction) * speed;
        double yMovementValue = (differenceY / direction) * speed;
        this.direction = new CartesianCoordinate(xMovementValue, yMovementValue);
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinate getDirection() {
        return this.direction;
    }

}

