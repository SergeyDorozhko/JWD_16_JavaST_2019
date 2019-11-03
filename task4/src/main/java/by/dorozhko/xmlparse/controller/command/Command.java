package by.dorozhko.xmlparse.controller.command;

import by.dorozhko.xmlparse.entity.TariffType;
import by.dorozhko.xmlparse.services.exception.ServiceException;

import java.util.Set;


public interface Command {

    /**
     * Method execute user request and take service to do action.
     * @param query user request.
     * @return set of tariffs.
     * @throws ServiceException exception of service.
     */
    Set<TariffType> execute(String query) throws ServiceException;

}
