import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores the tasks currently known to Summer as a managed list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final int capacity;

    /**
     * Creates an empty task list with the given maximum number of tasks.
     *
     * @param capacity maximum number of tasks this list can store
     */
    public TaskList(int capacity) {
        this.tasks = new ArrayList<>();
        this.capacity = capacity;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param index zero-based position of the task
     * @return task at the given index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes and returns the task at the given zero-based index.
     *
     * @param index zero-based position of the task to remove
     * @return removed task
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Checks whether the given zero-based index refers to a stored task.
     *
     * @param index zero-based position to check
     * @return true if there is a task at the given index
     */
    public boolean hasTaskAt(int index) {
        return index >= 0 && index < this.tasks.size();
    }

    /**
     * Returns a numbered display of all tasks in the list.
     *
     * @return formatted task list ready to print
     */
    public String list() {
        StringBuilder builder = new StringBuilder(" Here are the tasks in your list:");
        String lineSeparator = System.lineSeparator();

        for (int i = 0; i < this.tasks.size(); i++) {
            builder.append(lineSeparator)
                    .append(" ")
                    .append(i + 1)
                    .append(".")
                    .append(this.tasks.get(i));
        }

        return builder.toString();
    }

    /**
     * Returns a numbered display of tasks that occur on the given date.
     *
     * @param date date to filter tasks by
     * @return formatted list of tasks occurring on the given date, ready to print
     */
    public String listOn(LocalDate date) {
        StringBuilder builder = new StringBuilder(" Here are the tasks on " + date.format(Task.DATE_DISPLAY_FORMAT) + ":");
        String lineSeparator = System.lineSeparator();
        int count = 0;

        for (Task task : this.tasks) {
            if (task.occursOn(date)) {
                count++;
                builder.append(lineSeparator).append(" ").append(count).append(".").append(task);
            }
        }

        return builder.toString();
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return number of stored tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks whether this list has reached its capacity.
     *
     * @return true if no more tasks can be added
     */
    public boolean isFull() {
        return this.tasks.size() == this.capacity;
    }

}
