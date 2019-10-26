package by.dorozhko.xmlparse.controller.command;

import by.dorozhko.xmlparse.services.ServiceProvider;

public class StAXCommand implements Command {
    @Override
    public String execute(String query) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        return serviceProvider.getService().parseStAX(query);
    }
}
