package by.dorozhko.composite.controller.command;

import by.dorozhko.composite.services.Service;
import by.dorozhko.composite.services.ServiceFactory;

public class CreateCompositeFromData implements Command {
    /**
     * Method connecting with services.
     *
     * @param params query.
     * @return result.
     */
    @Override
    public String execute(final String params) {
        ServiceFactory provider = ServiceFactory.getInstance();
        Service services = provider.getService();

        return services.createCompositeFromData(params);

    }
}
