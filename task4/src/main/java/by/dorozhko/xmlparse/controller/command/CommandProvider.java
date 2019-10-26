package by.dorozhko.xmlparse.controller.command;

import by.dorozhko.xmlparse.controller.command.impl.BuildCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private Map<String, Command> command = new HashMap<>();

    private CommandProvider() {
        command.put("BUILD", new BuildCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }


    public Command getCommand(String commandName) {
        return command.get(commandName);
    }

}
