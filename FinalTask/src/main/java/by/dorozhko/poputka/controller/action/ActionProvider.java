package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.controller.action.impl.DisplayAllUsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ActionProvider {

    private final Logger logger = LogManager.getLogger(getClass().getName());
    private static final ActionProvider INSTANCE = new ActionProvider();

    private ActionProvider() {

    }

    public static ActionProvider getInstance() {
        return INSTANCE;
    }

    public Action getAction(final String action) {
        Action actionByQuery = null;
        switch (action) {
            case "list":
                actionByQuery = new DisplayAllUsers();
                break;
            default:
                logger.warn("This Action does not exist: ", action);
        }
        return actionByQuery;
    }
}
