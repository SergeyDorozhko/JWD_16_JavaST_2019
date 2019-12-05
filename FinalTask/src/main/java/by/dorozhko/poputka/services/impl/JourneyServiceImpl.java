package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.AbstractService;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class JourneyServiceImpl extends AbstractService implements JourneyService {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public List<Journey> findAllJourneyShort() {
        List<Journey> list = null;
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        transaction.begin(journeyDAO, userDAO, catalogDAO);
        try {
            list = journeyDAO.findAll();
            for (Journey journey : list) {
                User user = userDAO.findEntityById(journey.getDriver().getId());
                journey.getDriver().setName(user.getName());
                journey.setStartAddress(catalogDAO.getAddressByCityId(
                        Integer.parseInt(journey.getStartAddress().getCity())));
                journey.setDestinationAddress(catalogDAO.getAddressByCityId(
                        Integer.parseInt(journey
                                .getDestinationAddress().getCity())));
            }

            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
        }

        transaction.end();
        return list;
    }


    @Override
    public Journey createNewJourney(Journey journey) throws ExceptionService {
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        //TODO REALISE TAKING DATA FROM CATALOG
        transaction.begin(journeyDAO, userDAO);

        try {
            int userInfoId = userDAO.findUserInfoIdByUsersId(journey.getDriver().getId());
            journey.getDriver().setId(userInfoId);

            journey = journeyDAO.create(journey);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
            throw new ExceptionService(exceptionDao);
        } finally {
            transaction.end();
        }

        return journey;
    }
}
