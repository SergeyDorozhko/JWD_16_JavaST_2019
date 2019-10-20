package by.dorozhko.composite.controller.command;

import by.dorozhko.composite.services.Service;
import by.dorozhko.composite.services.ServiceFactory;

public class ViewTextFromRepository implements Command {

    /**
     * command to view text from storage.
     * @param params user action.
     * @return
     */
    @Override
    public String execute(final String params) {
        ServiceFactory provider = ServiceFactory.getInstance();
        Service services = provider.getService();
        return services.viewTextFromRepository();
    }
}
