package summer;

import java.nio.file.Path;

import summer.command.Command;
import summer.parser.Parser;
import summer.storage.Storage;
import summer.task.Task;
import summer.task.TaskList;
import summer.ui.Ui;

/**
 * Entry point of the Summer chatbot. Wires together the {@link Ui},
 * {@link Storage}, and {@link TaskList}, then runs the read-parse-execute
 * loop until the user types {@code bye} or input ends.
 */
public class Summer {
    private static final int MAX_TASKS = 100;
    private static final Path DATA_FILE_PATH = Path.of("data", "summer.txt");

    /**
     * Starts the chatbot, stores each task, lists saved tasks, and exits on bye.
     *
     * @param args command line arguments, not used by this program
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(DATA_FILE_PATH);
        TaskList tasks = new TaskList(MAX_TASKS);
        // Load tasks from disk
        for (Task task : storage.load()) {
            tasks.add(task);
        }

        ui.showWelcome();
        boolean isExit = false;
        String fullCommand;
        while (!isExit && (fullCommand = ui.readCommand()) != null) {
            ui.showLine();
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (SummerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }
}
