package by.dorozhko.xmlparse.controller.command.impl;

import by.dorozhko.xmlparse.controller.command.Command;
import by.dorozhko.xmlparse.services.Service;
import by.dorozhko.xmlparse.services.ServiceProvider;
import by.dorozhko.xmlparse.services.exception.ServiceException;
import by.dorozhko.xmlparse.entity.TariffType;

import java.util.Set;

public class ParseCommand implements Command {

    /**
     * Connect to the service which validate then parse xml.
     *
     * @param query path to xml and parser type.
     * @return Set of tariffs.
     * @throws ServiceException if xml not valid.
     */
    @Override
    public Set<TariffType> execute(final String query) throws ServiceException {
        ServiceProvider provider = ServiceProvider.getInstance();
        Service service = provider.getService();

        return service.buildTariffs(query);

    }
}
