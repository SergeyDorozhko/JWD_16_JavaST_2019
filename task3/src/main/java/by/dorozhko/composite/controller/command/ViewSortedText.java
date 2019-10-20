package by.dorozhko.composite.controller.command;

import by.dorozhko.composite.services.ServiceFactory;

public class ViewSortedText implements Command {
    /**
     * command to view sorted text.
     * @param params user action.
     * @return text to display.
     */
    @Override
    public String execute(final String params) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        return serviceFactory.getService().viewSortedText(params);
    }
}
