package by.dorozhko.transport.command.impl;

import by.dorozhko.transport.command.Command;
import by.dorozhko.transport.services.ServiceProvider;
import by.dorozhko.transport.services.Services;

public class DeleteEntityById implements Command {
    @Override
    public String execute(final String params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        Services services = provider.getService();


        String result = services.deleteEntity(params);

        return result + " -  successesfully removed.";

    }
}
