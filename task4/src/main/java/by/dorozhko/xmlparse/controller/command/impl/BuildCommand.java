package by.dorozhko.xmlparse.controller.command.impl;

import by.dorozhko.xmlparse.controller.command.Command;
import by.dorozhko.xmlparse.services.ServiceProvider;

public class BuildCommand implements Command {

    @Override
    public String execute(String query) {
        return ServiceProvider.getInstance().getService().buildTariffs(query);
    }
}
