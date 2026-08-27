package summer.command;

import summer.storage.Storage;
import summer.task.Task;
import summer.task.TaskList;
import summer.ui.Ui;

/** The "mark INDEX" command: marks a task as done. */
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.hasTaskAt(this.taskIndex)) {
            ui.showTaskNotFound();
            return;
        }
        Task task = tasks.get(this.taskIndex);
        task.markAsDone();
        storage.save(tasks);
        ui.showTaskMarked(task);
    }
}
