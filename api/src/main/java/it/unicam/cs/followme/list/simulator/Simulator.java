package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.model.commands.Command;

import java.util.List;

public interface Simulator {
    /**
     * Sets the program to be executed
     *
     * @param programList the program to be executed
     */
    void setProgramList(List<Command> programList);

    /**
     * Simulates the execution of the program
     *
     * @param delta_t         the time interval between two consecutive executions
     * @param execution_time the total time of the execution
     * @param numberOfCommandForExecution the number of commands to be executed in each simulation step
     */
    void simulate(double delta_t, double execution_time, int numberOfCommandForExecution);
    /**
     * Initializes the simulator
     */
    void init();
}
