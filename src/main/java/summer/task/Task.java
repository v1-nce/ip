package summer.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task entered by the user.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private final TaskType type;
    private final String description;
    private boolean isDone;

    /**
     * Creates a task with the given type, description, and completion status.
     *
     * @param type category of this task
     * @param description text describing the task
     * @param isDone whether the task is already completed
     */
    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
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

    /**
     * Returns the display text for this task.
     *
     * @return task type, status, and description
     */
    @Override
    public String toString() {
        return "[" + this.type.getIcon() + "][" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns this task's representation for the save file, as a
     * pipe-delimited line: type icon, done flag (1/0), and description.
     * Subclasses append their own extra fields.
     *
     * @return pipe-delimited save line for this task
     */
    public String toSaveFormat() {
        return this.type.getIcon() + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Checks whether this task occurs on the given date.
     * A task without a date (e.g. a todo) never occurs on any date.
     *
     * @param date date to check
     * @return true if this task occurs on the given date
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }
}
