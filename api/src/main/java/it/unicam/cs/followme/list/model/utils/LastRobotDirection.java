package it.unicam.cs.followme.list.model.utils;

public class LastRobotDirection implements MovementDirection {

    private Coordinate direction;
    private int speed;

    // TODO: 05/11/2023 passare coordinate invece di differenceX e differenceY, rimuovere speed
    public LastRobotDirection(double differenceX, double differenceY, int speed) {
        double direction = Math.sqrt(Math.pow(differenceX,2)+Math.pow(differenceY,2));
        this.speed = speed;
        double xMovementValue = (differenceX /direction)*speed;
        double yMovementValue = (differenceY /direction)*speed;
        this.direction = new CartesianCoordinate(xMovementValue, yMovementValue);
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinate getDirection(){
        return this.direction;
    }
//    private CartesianCoordinate calculateDirection(Coordinate robotPosition, Coordinate targetPosition, int speed) {
//        double startingRobotX = robotPosition.getX();
//        double startingRobotY = robotPosition.getY();
//        double distanceX = targetPosition.getX() - robotPosition.getX();
//        double distanceY = targetPosition.getY() - robotPosition.getY();
//        double totalDistance = Math.sqrt(Math.pow(distanceX,2) + Math.pow(distanceY,2));
//        double neededTime = totalDistance / speed;
//        double stepX = distanceX / neededTime;
//        double stepY = distanceY / neededTime;
//        for(double time = 0; time <= neededTime; time += 1.0) {
//            startingRobotX += stepX;
//            startingRobotY += stepY;
//        }
//        return new CartesianCoordinate(startingRobotX, startingRobotY);
//    }
//
}
