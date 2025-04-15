import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Kernel implementation of AITaskScheduler using a List<Task> representation.
 *
 * Convention: - No duplicate task names - Task names are non-null, non-empty -
 * Priorities are non-negative - Deadlines are non-null
 *
 * Correspondence: - Each Task represents a scheduled entry - The list
 * corresponds to the full scheduler - The next task is defined by highest
 * priority and earliest deadline
 */
public class AITaskScheduler1L extends AITaskSchedulerSecondary {

    private static class Task {
        String name;
        int priority;
        Date deadline;

        Task(String name, int priority, Date deadline) {
            this.name = name;
            this.priority = priority;
            this.deadline = deadline;
        }
    }

    private final List<Task> tasks;

    public AITaskScheduler1L() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void addTask(String name, int priority, Date deadline) {
        for (Task t : this.tasks) {
            assert !t.name.equals(name) : "Duplicate task name not allowed.";
        }
        this.tasks.add(new Task(name, priority, deadline));
    }

    @Override
    public String getNextTask() {
        if (this.tasks.isEmpty())
            return null;

        Task best = Collections.max(this.tasks,
                Comparator.comparingInt((Task t) -> t.priority)
                        .thenComparing(t -> t.deadline));

        return best.name;
    }

    @Override
    public void removeTask(String name) {
        this.tasks.removeIf(t -> t.name.equals(name));
    }

    @Override
    public boolean hasTask(String name) {
        return this.tasks.stream().anyMatch(t -> t.name.equals(name));
    }

    // Optional: Implement clear() and newInstance() if extending Standard directly
}
