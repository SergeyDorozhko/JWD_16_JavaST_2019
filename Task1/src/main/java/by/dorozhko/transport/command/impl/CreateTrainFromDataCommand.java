package by.dorozhko.transport.command.impl;

import by.dorozhko.transport.command.Command;
import by.dorozhko.transport.services.ServiceProvider;
import by.dorozhko.transport.services.Services;
import by.dorozhko.transport.services.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateTrainFromDataCommand implements Command {

    private Logger logger = LogManager.getLogger(CreateTrainFromDataCommand.class.getName());

    @Override
    public String execute(String params) {
        ServiceProvider provider = ServiceProvider.getInstance();
        Services services = provider.getService();
        boolean result = true;
        try {
            result = services.addEntityFromData();
            logger.trace("Add entity from data result : " + result);
        } catch (ServiceException e) {
            logger.error(e);
            return "Something went wrong!";
        }
        if (result) {
            return "Successfully read from database.";
        }
        return "Something went wrong!";
    }
}
