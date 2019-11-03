package by.dorozhko.xmlparse.services;

import by.dorozhko.xmlparse.services.exception.ServiceException;
import by.dorozhko.xmlparse.entity.TariffType;

import java.util.Set;

public interface Service {
    /**
     * build tariff method.
     *
     * @param pathToXMLandXSD Path to xml.
     * @return set of tariffs.
     * @throws ServiceException exception covering dao
     *                          exception or of file not valid.
     */
    Set<TariffType> buildTariffs(String pathToXMLandXSD)
            throws ServiceException;
}
