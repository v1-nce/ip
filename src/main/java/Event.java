/**
 * Represents a task that starts and ends at specific dates or times.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates an event task with the given description, start, end, and completion status.
     *
     * @param description text describing the task
     * @param from date or time when the task starts
     * @param to date or time when the task ends
     * @param isDone whether the task is already completed
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the display text for this event task.
     *
     * @return task type, status, description, start time, and end time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
