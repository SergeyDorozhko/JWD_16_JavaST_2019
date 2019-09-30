package by.dorozhko.matrix.controller.command;

import by.dorozhko.matrix.controller.command.impl.CreateMatrix;
import by.dorozhko.matrix.controller.command.impl.DisplayMatrix;
import by.dorozhko.matrix.controller.command.impl.InitialiseMainDiagonal;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();


    private Map<String, Command> commandMap = new HashMap<>();


    private CommandProvider(){
        commandMap.put("CreateMatrix", new CreateMatrix());
        commandMap.put("DisplayMatrix", new DisplayMatrix());
        commandMap.put("InitialiseMainDiagonal", new InitialiseMainDiagonal());
    }


    public static CommandProvider getInstance(){
        return instance;
    }

    public Command getCommand(String command){
        return commandMap.get(command);
    }
}
