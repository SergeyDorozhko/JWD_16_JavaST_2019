package by.dorozhko.xmlparse.controller;

import by.dorozhko.xmlparse.controller.command.Command;
import by.dorozhko.xmlparse.controller.command.CommandProvider;

public class Controller {
    public String doAction(String query){
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(query.split("-")[0]);
        return command.execute(query);
    }
}
