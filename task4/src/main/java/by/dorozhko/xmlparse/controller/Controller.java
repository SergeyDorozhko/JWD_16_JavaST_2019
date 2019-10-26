package by.dorozhko.xmlparse.controller;

import by.dorozhko.xmlparse.controller.command.Command;
import by.dorozhko.xmlparse.controller.command.CommandProvider;

public class Controller {
    public String doAction(String query){
        CommandProvider commandProvider = CommandProvider.getInstance();
        String[] commandThenPath = query.split("-");
        Command command = commandProvider.getCommand(commandThenPath[0]);
        System.out.println(commandThenPath[0]);
        return command.execute(commandThenPath[1]);
    }
}
