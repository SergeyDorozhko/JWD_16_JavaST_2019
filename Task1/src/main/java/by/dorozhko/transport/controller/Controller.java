package by.dorozhko.transport.controller;

import by.dorozhko.transport.command.Command;
import by.dorozhko.transport.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

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
        String[] action = request.split("[|]");
        logger.debug("User request: " + action[0] + ", params : " + action[1]);
        String result;

        Command command = commandProvider.getCommand(action[0]);
        result = command.execute(action[1]);
        logger.trace("Have result (result length): " + result.length() + "symbols");
        return result;
    }
}
