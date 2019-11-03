package by.dorozhko.xmlparse.services;

import by.dorozhko.xmlparse.services.exception.ServiceException;
import by.dorozhko.xmlparse.entity.TariffType;

import java.util.Set;

public interface Service {
    Set<TariffType> buildTariffs(String pathToXMLandXSD) throws ServiceException;
}
