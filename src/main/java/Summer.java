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
        String[] tasks = new String[MAX_TASKS];
        int taskCount = 0;

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            System.out.println(SEPARATOR);
            if (command.equals("bye")) {
                System.out.println(" Goodbye! Have an amazing day ahead!");
                System.out.println(SEPARATOR);
                break;
            }

            if (command.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(SEPARATOR);
                continue;
            }

            if (taskCount == MAX_TASKS) {
                System.out.println(" Sorry, I can only store up to 100 tasks!");
            } else {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println(" added: " + command);
            }
            System.out.println(SEPARATOR);
        }
        scanner.close();
    }
}
