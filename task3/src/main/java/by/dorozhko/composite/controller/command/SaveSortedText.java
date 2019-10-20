package by.dorozhko.composite.controller.command;

import by.dorozhko.composite.services.ServiceFactory;

public class SaveSortedText implements Command {

    /**
     * save sorted text command.
     * @param params user action (to sort by and save to).
     * @return result.
     */
    @Override
    public String execute(final String params) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        return serviceFactory.getService().saveSortedText(params);
    }
}
