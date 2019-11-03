package by.dorozhko.xmlparse.services.impl;

import by.dorozhko.xmlparse.dao.DAOProvider;
import by.dorozhko.xmlparse.dao.InterfaceValidator;
import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.services.Service;
import by.dorozhko.xmlparse.services.exception.ServiceException;
import by.dorozhko.xmlparse.entity.TariffType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class ServiceImpl implements Service {

    private Logger logger = LogManager.getLogger(getClass().getName());


    @Override
    public Set<TariffType> buildTariffs(String pathToXMLandXSD) throws ServiceException{

        logger.debug("start xml validation");


        DAOProvider provider = DAOProvider.getInstance();
        InterfaceValidator validator = provider.getValidator();

        if(!validator.validate(pathToXMLandXSD.split(";")[1], pathToXMLandXSD.split(";")[2])){
            logger.debug("xml is valid");
            throw new ServiceException("XML don't Valid");
        }

        logger.debug("xml is valid");



        TariffsBuilder builder = provider.getParser(pathToXMLandXSD.split(";")[0]);

        builder.buildSetTariffs(pathToXMLandXSD.split(";")[1], pathToXMLandXSD.split(";")[2]);
        return builder.getTariffs();

    }


}
