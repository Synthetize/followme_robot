package it.unicam.cs.followme.list.utils;

/**
 * This class is used to keep track of the execution time of a program
 * and to stop the execution when the time is over
 */
public class SimulationTimer {

    private static double simulationCurrentTime = 0;
    private static double simulationEndTime = 0;

    /**
     * Checks if the execution is over
     *
     * @return true if the execution is over, false otherwise
     */
    public boolean isExecutionOver() {
        return simulationCurrentTime >= simulationEndTime;
    }

    /**
     * Sets the end time of the execution
     *
     * @param simulationEndTime the end time of the execution
     */
    public static void setSimulationEndTime(double simulationEndTime) {
        SimulationTimer.simulationEndTime = simulationEndTime;
    }

    /**
     * Sets the current time of the execution
     *
     * @param simulationCurrentTime the current time of the execution
     */
    public static void setSimulationCurrentTime(double simulationCurrentTime) {
        SimulationTimer.simulationCurrentTime = simulationCurrentTime;
    }

    /**
     * Increments the current time of the execution
     */
    public static void incrementSimulationCurrentTime() {
        simulationCurrentTime++;
    }

}
