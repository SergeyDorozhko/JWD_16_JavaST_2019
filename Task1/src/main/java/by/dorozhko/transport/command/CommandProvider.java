package by.dorozhko.transport.command;

import by.dorozhko.transport.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    /**
     * Log4g, logging User request.
     */
    private Logger logger = LogManager.getLogger(CommandProvider.class.getName());

    private static final CommandProvider instance = new CommandProvider();
    private Map<String, Command> command = new HashMap<>();

    private CommandProvider() {
        command.put("readFromFile", new CreateTrainFromDataCommand());
        command.put("add", new AddEntityByUser());
        command.put("delete", new DeleteEntityById());
        command.put("specificationQuery", new SpecificationQuery());
        command.put("countPassengers", new CountPassengers());
        command.put("countBaggage", new CountBaggage());
        command.put("numberOfPassengersBetween", new NumberOfPassengersBetween());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        logger.trace(commandName);
        return command.get(commandName);
    }
}
