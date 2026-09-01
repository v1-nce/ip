package summer.ui;

import java.util.Scanner;

import summer.task.Task;

/**
 * Handles interaction between Summer and the user: reading command lines from
 * standard input and reporting responses.
 *
 * <p>Every {@code show*} method both prints to standard output (for the text CLI)
 * and appends to a buffer; the GUI reads that buffer via {@link #flush()}.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String BANNER = "                                           \n"
            + " ___ _   _ _ __ ___  _ __ ___   ___ _ __ \n"
            + "/ __| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|\n"
            + "\\__ \\ |_| | | | | | | | | | | |  __/ |   \n"
            + "|___/\\__,_|_| |_| |_|_| |_| |_|\\___|_|   \n";

    private final Scanner scanner;
    private final StringBuilder buffer = new StringBuilder();

    /** Creates a UI that reads commands from standard input. */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns everything buffered since the last flush and clears the buffer.
     *
     * @return the accumulated response text, without a trailing newline
     */
    public String flush() {
        String text = this.buffer.toString().stripTrailing();
        this.buffer.setLength(0);
        return text;
    }

    /** Prints the separator line shown between chatbot turns. */
    public void showLine() {
        System.out.println(SEPARATOR);
    }

    /** Prints the startup banner and greeting. */
    public void showWelcome() {
        showLine();
        System.out.println(BANNER);
        emit("Hello! I'm Summer.");
        emit("What can I do for you?");
    }

    /**
     * Returns the next command line typed by the user.
     *
     * @return the next command line, or {@code null} at end of input
     */
    public String readCommand() {
        return this.scanner.hasNextLine() ? this.scanner.nextLine() : null;
    }

    /** Releases the input source. Call once when Summer exits. */
    public void close() {
        this.scanner.close();
    }

    /** Prints the farewell message shown when the user exits. */
    public void showGoodbye() {
        emit(" Goodbye! Have an amazing day ahead!");
    }

    /** Prints {@code message} prefixed with "OOPS!!!", Summer's error format. */
    public void showError(String message) {
        emit(" OOPS!!! " + message);
    }

    /** Prints a message as-is, e.g. a pre-formatted task listing. */
    public void showMessage(String message) {
        emit(message);
    }

    /** Prints the message shown when a task number given by the user does not exist. */
    public void showTaskNotFound() {
        emit(" Sorry, that task number does not exist!");
    }

    /** Prints confirmation that {@code task} was added; {@code taskCount} is the new list size. */
    public void showTaskAdded(Task task, int taskCount) {
        emit(" Got it. I've added this task:");
        emit("   " + task);
        emit(" Now you have " + taskCount + " tasks in the list.");
    }

    /** Prints confirmation that {@code task} was removed; {@code taskCount} is the new list size. */
    public void showTaskDeleted(Task task, int taskCount) {
        emit(" Noted. I've removed this task:");
        emit("   " + task);
        emit(" Now you have " + taskCount + " tasks in the list.");
    }

    /** Prints confirmation that {@code task} was marked done. */
    public void showTaskMarked(Task task) {
        emit(" Nice! I've marked this task as done:");
        emit("   " + task);
    }

    /** Prints confirmation that {@code task} was marked not done. */
    public void showTaskUnmarked(Task task) {
        emit(" OK, I've marked this task as not done yet:");
        emit("   " + task);
    }

    /** Prints one line to standard output and records it in the response buffer. */
    private void emit(String line) {
        System.out.println(line);
        this.buffer.append(line).append(System.lineSeparator());
    }
}
