/** The "delete INDEX" command: removes a task from the list. */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.hasTaskAt(this.taskIndex)) {
            ui.showTaskNotFound();
            return;
        }
        Task task = tasks.delete(this.taskIndex);
        storage.save(tasks);
        ui.showTaskDeleted(task, tasks.size());
    }
}
