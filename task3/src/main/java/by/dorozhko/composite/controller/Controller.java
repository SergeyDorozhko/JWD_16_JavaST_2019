package by.dorozhko.composite.controller;

import by.dorozhko.composite.controller.command.Command;
import by.dorozhko.composite.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    /**
     * Logger of the class controller.
     */
    private Logger logger = LogManager.getLogger(Controller.class.getName());
    /**
     * Connect commandProvider.
     */
    private CommandProvider commandProvider = CommandProvider.getInstance();

    /**
     * Method connect user request with correct service.
     *
     * @param request what to do.
     * @return result.
     */
    public String doAction(final String request) {
        logger.debug(request);
        String[] action = request.split("[|]", 2);
        String result;

        Command command = commandProvider.
                getCommand(action[0]);
        result = command.execute(action[1]);
        return result;
    }
}
