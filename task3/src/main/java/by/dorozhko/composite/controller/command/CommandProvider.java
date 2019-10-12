package by.dorozhko.composite.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    /**
     * Log4g, logging User request.
     */
    private Logger logger = LogManager.
            getLogger(CommandProvider.class.getName());

    /**
     * Creating singletone.
     */
    private static final CommandProvider instance = new CommandProvider();

    /**
     * private constructor.
     */
    private Map<String, Command> command =
            new HashMap<>();

    private CommandProvider() {
        command.put("CreateCompositeFromData", new CreateCompositeFromData());
        command.put("ViewCompositeTextFromRepository", new ViewTextFromRepository());
        command.put("SaveTextToData", new SaveTextToData());
        command.put("ViewSortedCompositeTextFromRepository", new ViewSortedText());


    }

    /**
     * Sinlgetone creator.
     *
     * @return instace.
     */
    public static CommandProvider getInstance() {
        return instance;
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
