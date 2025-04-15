// File: test/components/taskscheduler/AITaskScheduler1LTest.java

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AITaskScheduler1LTest {

    private AITaskScheduler scheduler;

    @Before
    public void setUp() {
        this.scheduler = new AITaskScheduler1L();
    }

    @Test
    public void testAddAndHasTask() {
        this.scheduler.addTask("Project", 5, new Date());
        assertTrue(this.scheduler.hasTask("Project"));
    }

    @Test
    public void testRemoveTask() {
        this.scheduler.addTask("Project", 5, new Date());
        this.scheduler.removeTask("Project");
        assertFalse(this.scheduler.hasTask("Project"));
    }

    @Test
    public void testGetNextTaskByPriority() {
        this.scheduler.addTask("Low", 1,
                new Date(System.currentTimeMillis() + 200000));
        this.scheduler.addTask("High", 10,
                new Date(System.currentTimeMillis() + 100000));
        assertEquals("High", this.scheduler.getNextTask());
    }

    @Test
    public void testGetNextTaskByDeadlineTieBreaker() {
        Date now = new Date();
        this.scheduler.addTask("TaskA", 5, new Date(now.getTime() + 100000));
        this.scheduler.addTask("TaskB", 5, new Date(now.getTime() + 50000));
        assertEquals("TaskB", this.scheduler.getNextTask());
    }
}
