import java.util.Scanner;

public class Summer {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    /**
     * Starts the chatbot, stores each task, lists saved tasks, and exits on bye.
     *
     * @param args command line arguments, not used by this program
     */
    public static void main(String[] args) {
        String banner = "                                           \n"
                + " ___ _   _ _ __ ___  _ __ ___   ___ _ __ \n"
                + "/ __| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|\n"
                + "\\__ \\ |_| | | | | | | | | | | |  __/ |   \n"
                + "|___/\\__,_|_| |_| |_|_| |_| |_|\\___|_|   \n";

        System.out.println(SEPARATOR);
        System.out.println(banner);
        System.out.println("Hello! I'm Summer.");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList(MAX_TASKS);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            System.out.println(SEPARATOR);
            if (command.equals("bye")) {
                System.out.println(" Goodbye! Have an amazing day ahead!");
                System.out.println(SEPARATOR);
                break;
            }

            if (command.equals("list")) {
                System.out.println(tasks.list());
                System.out.println(SEPARATOR);
                continue;
            }

            if (command.startsWith("mark ")) {
                int taskIndex = getTaskIndex(command);
                if (tasks.hasTaskAt(taskIndex)) {
                    Task task = tasks.get(taskIndex);
                    task.markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + task);
                } else {
                    System.out.println(" Sorry, that task number does not exist!");
                }
                System.out.println(SEPARATOR);
                continue;
            }

            if (command.startsWith("unmark ")) {
                int taskIndex = getTaskIndex(command);
                if (tasks.hasTaskAt(taskIndex)) {
                    Task task = tasks.get(taskIndex);
                    task.markNotDone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + task);
                } else {
                    System.out.println(" Sorry, that task number does not exist!");
                }
                System.out.println(SEPARATOR);
                continue;
            }

            if (tasks.isFull()) {
                System.out.println(" Sorry, I can only store up to 100 tasks!");
            } else {
                Task task = createTask(command);
                tasks.add(task);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + task);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            }
            System.out.println(SEPARATOR);
        }
        scanner.close();
    }

    /**
     * Creates the task type represented by a user command.
     *
     * @param command user command describing a todo, deadline, or event
     * @return task represented by the command
     */
    private static Task createTask(String command) {
        if (command.startsWith("todo ")) {
            return new ToDo(command.substring("todo ".length()).trim(), false);
        }

        if (command.startsWith("deadline ")) {
            String details = command.substring("deadline ".length());
            int byIndex = details.indexOf(" /by ");
            String description = details.substring(0, byIndex).trim();
            String by = details.substring(byIndex + " /by ".length()).trim();
            return new Deadline(description, by, false);
        }

        if (command.startsWith("event ")) {
            String details = command.substring("event ".length());
            int fromIndex = details.indexOf(" /from ");
            int toIndex = details.indexOf(" /to ");
            String description = details.substring(0, fromIndex).trim();
            String from = details.substring(fromIndex + " /from ".length(), toIndex).trim();
            String to = details.substring(toIndex + " /to ".length()).trim();
            return new Event(description, from, to, false);
        }

        return new ToDo(command, false);
    }

    /**
     * Returns the zero-based task index from a command.
     *
     * @param command user command containing a task number after the first space
     * @return zero-based index of the requested task
     */
    private static int getTaskIndex(String command) {
        try {
            return Integer.parseInt(command.substring(command.indexOf(" ") + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
