import java.util.Scanner;

public class Summer {
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Starts the chatbot, echoes each user command, and exits when the user enters bye.
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
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            System.out.println(SEPARATOR);
            if (command.equals("bye")) {
                System.out.println(" Goodbye! Have an amazing day ahead!");
                System.out.println(SEPARATOR);
                break;
            }

            System.out.println(" " + command);
            System.out.println(SEPARATOR);
        }
        scanner.close();
    }
}
