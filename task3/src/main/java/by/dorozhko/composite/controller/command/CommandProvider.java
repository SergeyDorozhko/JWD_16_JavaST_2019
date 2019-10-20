package by.dorozhko.composite.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    /**
     * Log4g, logging User request.
     */
    private Logger logger = LogManager.
            getLogger(CommandProvider.class.getName());

    /**
     * Creating singletone.
     */
    private static final CommandProvider INSTANCE = new CommandProvider();

    /**
     * private constructor.
     */
    private Map<String, Command> command =
            new HashMap<>();

    private CommandProvider() {
        command.put("CreateCompositeFromData", new CreateCompositeFromData());
        command.put("ViewCompositeTextFromRepository",
                new ViewTextFromRepository());
        command.put("SaveTextToData", new SaveTextToData());
        command.put("ViewSortedCompositeTextFromRepository",
                new ViewSortedText());
        command.put("SaveSortedCompositeTextFromRepository",
                new SaveSortedText());


    }

    /**
     * Sinlgetone creator.
     *
     * @return instace.
     */
    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * send command to controller.
     *
     * @param commandName user command.
     * @return service.
     */
    public Command getCommand(final String commandName) {
        logger.trace(commandName);
        return command.get(commandName);
    }
}
