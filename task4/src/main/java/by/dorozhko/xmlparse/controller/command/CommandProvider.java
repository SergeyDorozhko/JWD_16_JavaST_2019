package by.dorozhko.xmlparse.controller.command;

import by.dorozhko.xmlparse.controller.command.impl.ParseCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    /**
     * Single tone.
     */
    private static final CommandProvider INSTANCE = new CommandProvider();

    /**
     * Map of supported commands.
     */
    private Map<String, Command> command = new HashMap<>();

    private CommandProvider() {
        command.put("DOM", new ParseCommand());
        command.put("SAX", new ParseCommand());
        command.put("StAX", new ParseCommand());

    }

    /**
     * Get link to command provider method.
     *
     * @return link t this object.
     */
    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    /**
     * get command method.
     *
     * @param commandName params of command.
     * @return link to needed command.
     */
    public Command getCommand(final String commandName) {
        return command.get(commandName);
    }

}
