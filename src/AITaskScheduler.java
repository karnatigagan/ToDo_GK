/**
 * Enhanced interface for AI Task Scheduler.
 */
//ok
public interface AITaskScheduler extends AITaskSchedulerKernel {

    /**
     * Adjusts the priority of an existing task.
     *
     * @param name
     *            the name of the task to update
     * @param newPriority
     *            the new priority value
     * @requires this.hasTask(name)
     * @ensures priority(name) = newPriority
     */
    void adjustPriority(String name, int newPriority);
}
