package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.JourneyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class JourneyServiceImpl implements JourneyService {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public List<Journey> findAllJourneyShort() {
        List<Journey> list = null;
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
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
}
