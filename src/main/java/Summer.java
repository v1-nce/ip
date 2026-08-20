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

            try {
                if (tasks.isFull()) {
                    System.out.println(" Sorry, I can only store up to 100 tasks!");
                } else {
                    Task task = createTask(command);
                    tasks.add(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                }
            } catch (SummerException e) {
                System.out.println(" OOPS!!! " + e.getMessage());
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
     * @throws SummerException if the command is unknown or missing required details
     */
    private static Task createTask(String command) throws SummerException {
        if (command.equals("todo") || command.startsWith("todo ")) {
            String description = command.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new SummerException("A todo needs a description.");
            }

            return new ToDo(description, false);
        }

        if (command.equals("deadline") || command.startsWith("deadline ")) {
            String details = command.substring("deadline".length()).trim();
            int byIndex = details.indexOf(" /by ");
            if (byIndex == -1) {
                throw new SummerException("A deadline needs this format: deadline DESCRIPTION /by WHEN");
            }

            String description = details.substring(0, byIndex).trim();
            String by = details.substring(byIndex + " /by ".length()).trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new SummerException("A deadline needs both a description and a /by value.");
            }

            return new Deadline(description, by, false);
        }

        if (command.equals("event") || command.startsWith("event ")) {
            String details = command.substring("event".length()).trim();
            int fromIndex = details.indexOf(" /from ");
            int toIndex = details.indexOf(" /to ");
            if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
                throw new SummerException("An event needs this format: event DESCRIPTION /from START /to END");
            }

            String description = details.substring(0, fromIndex).trim();
            String from = details.substring(fromIndex + " /from ".length(), toIndex).trim();
            String to = details.substring(toIndex + " /to ".length()).trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new SummerException("An event needs a description, /from value, and /to value.");
            }

            return new Event(description, from, to, false);
        }

        throw new SummerException("I don't know that command yet.");
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
