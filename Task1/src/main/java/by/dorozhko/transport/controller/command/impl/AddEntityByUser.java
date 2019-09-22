package by.dorozhko.transport.controller.command.impl;

import by.dorozhko.transport.controller.command.Command;
import by.dorozhko.transport.services.ServiceProvider;
import by.dorozhko.transport.services.Services;

public class AddEntityByUser implements Command {

    /**
     * Method connecting with services.
     * @param params query.
     * @return result.
     */
    @Override
    public String execute(final String params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        Services services = provider.getService();

        boolean result = services.addEntity(params);

        if (result) {
            return "Successfully added.";
        }

        return "Some thing go wrong. Incorrect value of inputed params.";

    }
}
