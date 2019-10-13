package by.dorozhko.composite.controller.command;

import by.dorozhko.composite.services.Service;
import by.dorozhko.composite.services.ServiceFactory;

public class ViewTextFromRepository implements Command {

    @Override
    public String execute(final String params) {
        ServiceFactory provider = ServiceFactory.getInstance();
        Service services = provider.getService();
        return services.viewTextFromRepository();
    }
}
