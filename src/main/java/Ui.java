import java.util.Scanner;

/**
 * Handles all interaction between Summer and the user: reading command
 * lines from standard input and printing responses to standard output.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String BANNER = "                                           \n"
            + " ___ _   _ _ __ ___  _ __ ___   ___ _ __ \n"
            + "/ __| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|\n"
            + "\\__ \\ |_| | | | | | | | | | | |  __/ |   \n"
            + "|___/\\__,_|_| |_| |_|_| |_| |_|\\___|_|   \n";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Prints the separator line shown between chatbot turns. */
    public void showLine() {
        System.out.println(SEPARATOR);
    }

    /** Prints the startup banner and greeting. */
    public void showWelcome() {
        showLine();
        System.out.println(BANNER);
        System.out.println("Hello! I'm Summer.");
        System.out.println("What can I do for you?");
    }

    /**
     * @return the next command line typed by the user, or {@code null} at end of input
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
        System.out.println(" Goodbye! Have an amazing day ahead!");
    }

    /** Prints {@code message} prefixed with "OOPS!!!", Summer's error format. */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    /** Prints a message as-is, e.g. a pre-formatted task listing. */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /** Prints the message shown when a task number given by the user does not exist. */
    public void showTaskNotFound() {
        System.out.println(" Sorry, that task number does not exist!");
    }

    /** Prints confirmation that {@code task} was added; {@code taskCount} is the new list size. */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /** Prints confirmation that {@code task} was removed; {@code taskCount} is the new list size. */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /** Prints confirmation that {@code task} was marked done. */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /** Prints confirmation that {@code task} was marked not done. */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }
}
