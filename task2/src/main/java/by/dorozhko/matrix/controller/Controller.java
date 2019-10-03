package by.dorozhko.matrix.controller;

import by.dorozhko.matrix.controller.command.Command;
import by.dorozhko.matrix.controller.command.CommandProvider;

public final class Controller {
    /**
     * Method connecting user by its request  to services.
     * @param request user request.
     * @return result of action.
     */
    public String doAction(final String request) {
        String[] actionThenSpecificatin = request.split(":");
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(actionThenSpecificatin[0]);

        return command.execute(actionThenSpecificatin[1]);
    }
}
