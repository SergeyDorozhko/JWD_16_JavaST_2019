package by.dorozhko.poputka.controller;

import by.dorozhko.poputka.controller.action.administrator.DisplayAllUsers;
import by.dorozhko.poputka.controller.action.JourneyList;
import by.dorozhko.poputka.controller.action.JourneyListForMainPage;
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
            case "/listOfUsers":
                actionByQuery = new DisplayAllUsers();
                break;
            case "/listOfJourneys":
                actionByQuery = new JourneyList();
                break;
            default:
                actionByQuery = new JourneyListForMainPage();
                logger.warn("Default, show main page");
        }
        return actionByQuery;
    }
}
