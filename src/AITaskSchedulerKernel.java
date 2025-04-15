import java.util.Date;

import components.standard.Standard;

/**
 * Kernel interface for AI Task Scheduler.
 */
public interface AITaskSchedulerKernel extends Standard<AITaskScheduler> {

    /**
     * Adds a task to the scheduler.
     *
     * @param name
     *            the name of the task
     * @param priority
     *            the priority of the task (higher is more important)
     * @param deadline
     *            the deadline of the task
     * @ensures this = #this + task(name, priority, deadline)
     */
    void addTask(String name, int priority, Date deadline);

    /**
     * Returns the next task based on priority and deadline.
     *
     * @return name of the highest priority task
     * @ensures <return> = task with highest priority and soonest deadline
     */
    String getNextTask();

    /**
     * Removes the task with the given name.
     *
     * @param name
     *            the name of the task to remove
     * @requires this.hasTask(name)
     * @ensures this = #this without task(name)
     */
    void removeTask(String name);

    /**
     * Checks if the task exists in the scheduler.
     *
     * @param name
     *            the name of the task
     * @return true if the task is in the scheduler
     * @ensures <return> = (∃ task with name = name)
     */
    boolean hasTask(String name);
}
