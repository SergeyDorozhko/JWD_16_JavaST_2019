package by.dorozhko.xmlparse.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private Map<String, Command> command = new HashMap<>();

    private CommandProvider() {
        command.put("DOM", new DOMCommand());
        command.put("SAX", new SAXCommand());
        command.put("StAX", new StAXCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }


    public Command getCommand(String commandName){
        return command.get(commandName);
    }

}
