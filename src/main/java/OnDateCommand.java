import java.time.LocalDate;

/** The "on DATE" command: shows every task that occurs on the given date. */
public class OnDateCommand extends Command {
    private final LocalDate date;

    public OnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.listOn(this.date));
    }
}
