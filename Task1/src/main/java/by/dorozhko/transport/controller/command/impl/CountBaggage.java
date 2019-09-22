package by.dorozhko.transport.controller.command.impl;

import by.dorozhko.transport.controller.command.Command;
import by.dorozhko.transport.services.ServiceProvider;
import by.dorozhko.transport.services.Services;

public class CountBaggage implements Command {
    /**
     * Command connecting controller with services counting baggage.
     *
     * @param params user action.
     * @return service.
     */
    @Override
    public String execute(final String params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        Services services = provider.getService();

        return services.countBaggage(params);
    }
}
