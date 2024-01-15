package it.unicam.cs.followme.list.simulator;

import it.unicam.cs.followme.list.model.commands.Command;
import it.unicam.cs.followme.list.model.robots.Robot;

import java.util.List;

public interface Simulator {
    /**
     * Sets the program to be executed
     * @param programList the program to be executed
     */
    void setProgramList(List<Command> programList);
    /**
     * Executes the program on the given robot
     * @param robot the robot on which the program will be executed
     */
    void simulate(double delta_t, double execution_time);
}
