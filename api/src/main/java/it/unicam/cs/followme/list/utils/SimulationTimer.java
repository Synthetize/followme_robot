package it.unicam.cs.followme.list.utils;
/**
 * This class is used to keep track of the execution time of a program
 * and to stop the execution when the time is over
 */
public class SimulationTimer {

        private static long simulationCurrentTime = 0;
        private static long simulationEndTime = 0;

        /**
         * Increments the timer if the execution is not over
         * @return true if the execution is over, false otherwise
         */
        public boolean incrementTimerIfNotOver() {
            if(simulationCurrentTime >= simulationEndTime) {
                return true;
            }
            simulationCurrentTime++;
            return false;
        }

        /**
         * Checks if the execution is over
         * @return true if the execution is over, false otherwise
         */
        public boolean isExecutionOver() {
            return simulationCurrentTime >= simulationEndTime;
        }

        /**
         * Sets the end time of the execution
         * @param simulationEndTime the end time of the execution
         */
        public static void setSimulationEndTime(long simulationEndTime) {
            SimulationTimer.simulationEndTime = simulationEndTime;
        }

        /**
         * Sets the current time of the execution
         * @param simulationCurrentTime the current time of the execution
         */
        public static void setSimulationCurrentTime(long simulationCurrentTime) {
            SimulationTimer.simulationCurrentTime = simulationCurrentTime;
        }

}
