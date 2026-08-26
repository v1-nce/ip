/**
 * Represents a task that should be completed by a specific date or time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates a deadline task with the given description, due time, and completion status.
     *
     * @param description text describing the task
     * @param by date or time by which the task should be completed
     * @param isDone whether the task is already completed
     */
    public Deadline(String description, String by, boolean isDone) {
        super(TaskType.DEADLINE, description, isDone);
        this.by = by;
    }

    /**
     * Returns the display text for this deadline task.
     *
     * @return task type, status, description, and due time
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns this deadline's save line, with the due time appended.
     *
     * @return pipe-delimited save line for this deadline
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.by;
    }
}
