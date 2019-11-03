package by.dorozhko.xmlparse.services.impl;

import by.dorozhko.xmlparse.dao.DAOProvider;
import by.dorozhko.xmlparse.dao.InterfaceProperties;
import by.dorozhko.xmlparse.dao.InterfaceValidator;
import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.services.Service;
import by.dorozhko.xmlparse.services.exception.ServiceException;
import by.dorozhko.xmlparse.entity.TariffType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class ServiceImpl implements Service {
    /**
     * logger.
     */
    private Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * access to dao layout.
     */
    private DAOProvider provider;

    /**
     * default constructor.
     */
    public ServiceImpl() {
        provider = DAOProvider.getInstance();
    }

    /**
     * method check xml for valid, and than take parser for parse.
     *
     * @param typeOfParserPathToXMLandRootCatalog Path to xml.
     * @return set of tariffs.
     * @throws ServiceException throws whe xml file not valid.
     */
    @Override
    public Set<TariffType> buildTariffs(
            final String typeOfParserPathToXMLandRootCatalog)
            throws ServiceException {

        logger.debug("try to get xsd");
        String rootCatalog = typeOfParserPathToXMLandRootCatalog.split(";")[2];
        String pathToXSD = getPathToXSDFromProperty(rootCatalog);

        logger.debug("xsd successfully taken", pathToXSD);

        logger.debug("start xml validation");


        InterfaceValidator validator = provider.getValidator();

        if (!validator.validate(typeOfParserPathToXMLandRootCatalog
                        .split(";")[1],
                pathToXSD)) {
            logger.debug("xml is valid");
            throw new ServiceException("XML don't Valid");
        }

        logger.debug("xml is valid");


        TariffsBuilder builder = provider.
                getParser(typeOfParserPathToXMLandRootCatalog.split(";")[0]);

        builder.buildSetTariffs(typeOfParserPathToXMLandRootCatalog
                        .split(";")[1],
                pathToXSD);
        return builder.getTariffs();

    }

    private String getPathToXSDFromProperty(final String rootCatalog) {

        InterfaceProperties properties = provider.getProperty();

        return properties.getProperty(rootCatalog);
    }

}
