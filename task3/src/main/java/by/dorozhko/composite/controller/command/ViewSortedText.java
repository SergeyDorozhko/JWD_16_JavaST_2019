package by.dorozhko.composite.controller.command;

import by.dorozhko.composite.services.ServiceFactory;

public class ViewSortedText implements Command {
    @Override
    public String execute(final String params) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        return serviceFactory.getService().viewSortedText(params);
    }
}
