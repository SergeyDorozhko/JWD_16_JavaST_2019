package by.dorozhko.transport.controller.command;

import by.dorozhko.transport.controller.command.impl.AddEntityByUser;
import by.dorozhko.transport.controller.command.impl.CountBaggage;
import by.dorozhko.transport.controller.command.impl.CountPassengers;
import by.dorozhko.transport.controller.command.impl.CreateTrainFromDataCommand;
import by.dorozhko.transport.controller.command.impl.DeleteEntityById;
import by.dorozhko.transport.controller.command.impl.NumberOfPassengersBetween;
import by.dorozhko.transport.controller.command.impl.SpecificationQuery;

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
    private static final CommandProvider instance = new CommandProvider();

    /**
     * private constructor.
     */
    private Map<String, Command> command =
            new HashMap<>();

    private CommandProvider() {
        command.put("readFromFile", new CreateTrainFromDataCommand());
        command.put("add", new AddEntityByUser());
        command.put("delete", new DeleteEntityById());
        command.put("specificationQuery", new SpecificationQuery());
        command.put("countPassengers", new CountPassengers());
        command.put("countBaggage", new CountBaggage());
        command.put("numberOfPassengersBetween",
                new NumberOfPassengersBetween());
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
