/**
 * Represents a task without any date or time attached to it.
 */
public class ToDo extends Task {
    /**
     * Creates a todo task with the given description and completion status.
     *
     * @param description text describing the task
     * @param isDone whether the task is already completed
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the display text for this todo task.
     *
     * @return task type, status, and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
