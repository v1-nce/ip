/**
 * Adds a new task (todo, deadline, or event).
 *
 * <p>Parsing is deferred to {@link #execute}, after the capacity check, so a
 * malformed command while the list is full still reports "list is full"
 * rather than a syntax error (matches Summer's original behavior).
 */
public class AddCommand extends Command {
    private final String rawCommand;

    public AddCommand(String rawCommand) {
        this.rawCommand = rawCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SummerException {
        if (tasks.isFull()) {
            ui.showMessage(" Sorry, I can only store up to 100 tasks!");
            return;
        }
        Task task = Parser.createTask(this.rawCommand);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }
}
