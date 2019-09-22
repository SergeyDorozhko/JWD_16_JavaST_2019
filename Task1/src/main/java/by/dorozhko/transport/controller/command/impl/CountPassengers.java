package by.dorozhko.transport.controller.command.impl;

import by.dorozhko.transport.controller.command.Command;
import by.dorozhko.transport.services.ServiceProvider;
import by.dorozhko.transport.services.Services;

public class CountPassengers implements Command {

    /**
     * Command connecting to the service.
     *
     * @param params user action.
     * @return setvice.
     */
    @Override
    public String execute(final String params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        Services services = provider.getService();
        return services.countPassengers(params);
    }
}
