package by.dorozhko.matrix.controller.command;

import by.dorozhko.matrix.controller.command.impl.CreateMatrix;
import by.dorozhko.matrix.controller.command.impl.DisplayMatrix;
import by.dorozhko.matrix.controller.command.impl.InitialiseMainDiagonal;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    /**
     * Singletone.
     */
    private static final CommandProvider INSTANCE = new CommandProvider();

    /**
     * Map of commands connecting with services.
     */
    private Map<String, Command> commandMap = new HashMap<>();
    private CommandProvider() {
        commandMap.put("CreateMatrix", new CreateMatrix());
        commandMap.put("DisplayMatrix", new DisplayMatrix());
        commandMap.put("InitialiseMainDiagonal", new InitialiseMainDiagonal());
    }

    /**
     * Creating link to single example of Command provider.
     * @return link.
     */
    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * Command take user request and connecting to the service.
     * @param command user request.
     * @return command to service.
     */
    public Command getCommand(final String command) {
        return commandMap.get(command);
    }
}
