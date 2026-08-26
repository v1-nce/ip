import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Makes sense of the raw command text typed by the user: decides which
 * {@link Command} it represents, and turns command text into the tasks,
 * dates, and indices those commands need.
 */
public class Parser {
    /**
     * Parses one full line of user input into the command it represents.
     *
     * @param fullCommand the raw command line typed by the user
     * @return the command to execute
     * @throws SummerException if the command text names an invalid date
     */
    public static Command parse(String fullCommand) throws SummerException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        }

        if (fullCommand.equals("list")) {
            return new ListCommand();
        }

        if (fullCommand.startsWith("on ")) {
            LocalDate date = parseDate(fullCommand.substring("on ".length()).trim());
            return new OnDateCommand(date);
        }

        if (fullCommand.startsWith("mark ")) {
            return new MarkCommand(getTaskIndex(fullCommand));
        }

        if (fullCommand.startsWith("unmark ")) {
            return new UnmarkCommand(getTaskIndex(fullCommand));
        }

        if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(getTaskIndex(fullCommand));
        }

        return new AddCommand(fullCommand);
    }

    /**
     * Creates the task type represented by a user command.
     *
     * @param command user command describing a todo, deadline, or event
     * @return task represented by the command
     * @throws SummerException if the command is unknown or missing required details
     */
    static Task createTask(String command) throws SummerException {
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

            return new Deadline(description, parseDate(by), false);
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

            return new Event(description, parseDate(from), parseDate(to), false);
        }

        throw new SummerException("I don't know that command yet.");
    }

    /**
     * Parses a date given by the user in yyyy-mm-dd format.
     *
     * @param text date text, expected as yyyy-mm-dd (e.g. 2019-10-15)
     * @return parsed date
     * @throws SummerException if the text is not a valid yyyy-mm-dd date
     */
    private static LocalDate parseDate(String text) throws SummerException {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            throw new SummerException("Please give dates as yyyy-mm-dd, e.g. 2019-10-15.");
        }
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
