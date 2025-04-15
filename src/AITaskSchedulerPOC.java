import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Proof-of-Concept for AI Task Scheduler Component. Prioritizes tasks based on
 * deadline and priority.
 */
public class AITaskSchedulerPOC {

    // Internal class to represent a Task
    private static class Task {
        String name;
        int priority; // Higher value = higher priority
        Date deadline;

        Task(String name, int priority, Date deadline) {
            this.name = name;
            this.priority = priority;
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return String.format("Task(name='%s', priority=%d, deadline=%s)",
                    this.name, this.priority, this.deadline);
        }
    }

    // List to hold tasks
    private final List<Task> tasks;

    public AITaskSchedulerPOC() {
        this.tasks = new ArrayList<>();
    }

    // Kernel Method 1: Add task
    public void addTask(String name, int priority, Date deadline) {
        this.tasks.add(new Task(name, priority, deadline));
    }

    // Kernel Method 2: Get next task based on highest priority, then earliest deadline
    public String getNextTask() {
        if (this.tasks.isEmpty())
            return null;

        Task next = Collections.max(this.tasks,
                Comparator.comparingInt((Task t) -> t.priority).thenComparing(
                        t -> t.deadline, Comparator.naturalOrder()));

        return next.name;
    }

    // Kernel Method 3: Remove task by name
    public void removeTask(String name) {
        this.tasks.removeIf(task -> task.name.equals(name));
    }

    // Kernel Method 4: Check if task exists
    public boolean hasTask(String name) {
        return this.tasks.stream().anyMatch(task -> task.name.equals(name));
    }

    // Secondary Method: Adjust the priority of a task
    public void adjustPriority(String name, int newPriority) {
        for (Task task : this.tasks) {
            if (task.name.equals(name)) {
                task.priority = newPriority;
                break;
            }
        }
    }

    // Show all tasks
    public void printTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks scheduled.");
        } else {
            for (Task task : this.tasks) {
                System.out.println(task);
            }
        }
    }

    // Proof-of-Concept main method
    public static void main(String[] args) {
        AITaskSchedulerPOC scheduler = new AITaskSchedulerPOC();

        // Sample deadlines
        Calendar cal = Calendar.getInstance();

        cal.set(2025, Calendar.MAY, 5);
        scheduler.addTask("Finish essay", 2, cal.getTime());

        cal.set(2025, Calendar.APRIL, 25);
        scheduler.addTask("Apply for internship", 5, cal.getTime());

        cal.set(2025, Calendar.APRIL, 20);
        scheduler.addTask("Buy groceries", 1, cal.getTime());

        System.out.println("All Tasks:");
        scheduler.printTasks();

        System.out.println("\nNext Task to Work On:");
        System.out.println(scheduler.getNextTask());

        System.out.println("\nAdjusting priority for 'Buy groceries'...");
        scheduler.adjustPriority("Buy groceries", 6);

        System.out.println("\nAll Tasks After Adjustment:");
        scheduler.printTasks();

        System.out.println("\nRemoving task 'Finish essay'...");
        scheduler.removeTask("Finish essay");

        System.out.println("\nAll Tasks After Removal:");
        scheduler.printTasks();

        System.out.println("\nHas task 'Apply for internship'? "
                + scheduler.hasTask("Apply for internship"));
        System.out.println("Has task 'Finish essay'? "
                + scheduler.hasTask("Finish essay"));
    }
}
