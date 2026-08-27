package summer.command;

import summer.storage.Storage;
import summer.task.Task;
import summer.task.TaskList;
import summer.ui.Ui;

/** The "unmark INDEX" command: marks a task as not done. */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.hasTaskAt(this.taskIndex)) {
            ui.showTaskNotFound();
            return;
        }
        Task task = tasks.get(this.taskIndex);
        task.markNotDone();
        storage.save(tasks);
        ui.showTaskUnmarked(task);
    }
}
