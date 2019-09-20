package by.dorozhko.transport.command.impl;

import by.dorozhko.transport.command.Command;
import by.dorozhko.transport.services.ServiceProvider;
import by.dorozhko.transport.services.Services;

public class CountBaggage implements Command {
    @Override
    public String execute(String params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        Services services = provider.getService();

        return services.countBaggage(params);
    }
}
