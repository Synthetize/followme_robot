package it.unicam.cs.followme.list.executor;
/**
 * This class is used to keep track of the execution time of a program
 * and to stop the execution when the time is over
 */
public abstract class ExecutionTimer {

        private static long startTime;
        private static long endTime;

        public ExecutionTimer(long startTime, long endTime) {
            ExecutionTimer.startTime = startTime;
            ExecutionTimer.endTime = endTime;
        }

        /**
         * Increments the timer if the execution is not over
         * @return true if the execution is over, false otherwise
         */
        public boolean incrementTimerIfNotOver() {
            if(startTime > endTime) {
                return true;
            }
            startTime++;
            return false;
        }

        /**
         * Checks if the execution is over
         * @return true if the execution is over, false otherwise
         */
        public boolean isExecutionOver() {
            return startTime > endTime;
        }

        /**
         * Sets the start time of the execution
         * @param endTime the end time of the execution
         */
        public void setEndTime(long endTime) {
            ExecutionTimer.endTime = endTime;
        }
}
