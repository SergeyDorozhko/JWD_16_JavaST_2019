package by.dorozhko.xmlparse.controller.command;

import by.dorozhko.xmlparse.services.ServiceProvider;

public class SAXCommand implements Command {
    @Override
    public String execute(String query) {
        return ServiceProvider.getInstance().getService().parseSAX(query);
    }
}
