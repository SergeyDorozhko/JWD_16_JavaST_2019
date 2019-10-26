package by.dorozhko.xmlparse.controller.command;

import by.dorozhko.xmlparse.services.ServiceProvider;

public class DOMCommand implements Command {

    @Override
    public String execute(String query) {
        return ServiceProvider.getInstance().getService().parseDOM(query);
    }
}
