
// File: test/components/taskscheduler/AITaskSchedulerSecondaryTest.java

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AITaskSchedulerSecondaryTest {

    private AITaskScheduler scheduler;

    @Before
    public void setUp() {
        this.scheduler = new AITaskScheduler1L();
    }

    @Test
    public void testAdjustPriority() {
        this.scheduler.addTask("Email", 3, new Date());
        this.scheduler.adjustPriority("Email", 10);
        assertEquals("Email", this.scheduler.getNextTask());
    }

    @Test
    public void testToStringOutput() {
        this.scheduler.addTask("A", 1, new Date());
        this.scheduler.addTask("B", 2, new Date());
        String output = this.scheduler.toString();
        assertTrue(output.contains("AITaskScheduler"));
        assertTrue(output.contains("A") || output.contains("B"));
    }

    @Test
    public void testEquals() {
        AITaskScheduler s1 = new AITaskScheduler1L();
        AITaskScheduler s2 = new AITaskScheduler1L();

        Date d = new Date();
        s1.addTask("Read", 1, d);
        s2.addTask("Read", 1, d);

        assertTrue(s1.equals(s2));
    }
}
