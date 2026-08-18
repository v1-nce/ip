/**
 * Represents a task entered by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description and completion status.
     *
     * @param description text describing the task
     * @param isDone whether the task is already completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the display icon for this task's completion status.
     *
     * @return {@code X} if done, otherwise a blank space
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markNotDone() {
        this.isDone = false;
    }
}
