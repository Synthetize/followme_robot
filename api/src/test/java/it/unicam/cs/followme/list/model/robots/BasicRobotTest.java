package it.unicam.cs.followme.list.model.robots;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicRobotTest {
    @Test
    void shouldAddLabel() {
        BasicRobot robot = new BasicRobot();
        robot.addLabel("LABEL_");
        assertTrue(robot.getCurrentConditionLabels().contains("LABEL_"));
    }

    @Test
    void shouldRemoveLabel() {
        BasicRobot robot = new BasicRobot();
        robot.addLabel("LABEL_");
        assertEquals(1, robot.getCurrentConditionLabels().size());
        robot.removeLabel("LABEL_");
        assertTrue(robot.getCurrentConditionLabels().isEmpty());
    }

}
