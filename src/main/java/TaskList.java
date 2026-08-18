/**
 * Stores the tasks currently known to Summer as a managed list.
 */
public class TaskList {
    private final Task[] tasks;
    private int taskCount;

    /**
     * Creates a task list with the given maximum number of tasks.
     *
     * @param capacity maximum number of tasks this list can store
     */
    public TaskList(int capacity) {
        this.tasks = new Task[capacity];
        this.taskCount = 0;
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        this.tasks[this.taskCount] = task;
        this.taskCount++;
    }

    /**
     * Returns the task at the given zero-based index.
     *
     * @param index zero-based position of the task
     * @return task at the given index
     */
    public Task get(int index) {
        return this.tasks[index];
    }

    /**
     * Returns a numbered display of all tasks in the list.
     *
     * @return formatted task list ready to print
     */
    public String list() {
        StringBuilder builder = new StringBuilder(" Here are the tasks in your list:");
        String lineSeparator = System.lineSeparator();

        for (int i = 0; i < this.taskCount; i++) {
            builder.append(lineSeparator)
                    .append(" ")
                    .append(i + 1)
                    .append(".")
                    .append(this.tasks[i]);
        }

        return builder.toString();
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return number of stored tasks
     */
    public int size() {
        return this.taskCount;
    }

    /**
     * Checks whether this list has reached its capacity.
     *
     * @return true if no more tasks can be added
     */
    public boolean isFull() {
        return this.taskCount == this.tasks.length;
    }
}
