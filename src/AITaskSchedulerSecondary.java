/**
 * Abstract class implementing all secondary methods of AITaskScheduler.
 */
public abstract class AITaskSchedulerSecondary implements AITaskScheduler {

    @Override
    public void adjustPriority(String name, int newPriority) {
        assert this.hasTask(name) : "Task must exist to adjust priority";
        // Step 1: Remove the task
        this.removeTask(name);
        // Step 2: Add it back with new priority and same deadline
        // But we have no way to get the original deadline via kernel...
        // So for now this will be left as a logical placeholder unless a `getDeadline()` kernel method is added
        // Example placeholder:
        // this.addTask(name, newPriority, originalDeadline);
        // Proper version of this requires an update to the kernel interface
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AITaskScheduler: [");

        boolean first = true;
        while (this.hasNext()) {
            if (!first) {
                sb.append(", ");
            }
            String task = this.getNextTask();
            sb.append(task);
            this.removeTask(task);
            first = false;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AITaskScheduler)) {
            return false;
        }
        AITaskScheduler other = (AITaskScheduler) obj;

        // Naive implementation: both must contain same tasks
        // This would ideally compare name, priority, deadline—requires getters or iteration support
        // You can leave a comment that this is a placeholder or implement logic after adding comparison kernel methods

        return this.toString().equals(other.toString());
    }
}
