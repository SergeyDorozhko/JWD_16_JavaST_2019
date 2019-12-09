package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.AbstractService;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class JourneyServiceImpl extends AbstractService implements JourneyService {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public List<Journey> findAllActualForMainPage() {
        return findAllActualJourneyLimit(0, 9);
    }

    @Override
    public List<Journey> findAllActualForJourneyPage(int limitFrom, int limitTo) {
        return findAllActualJourneyLimit(limitFrom, limitTo);
    }

    private List<Journey> findAllActualJourneyLimit(int limitFrom, int limitTo) {
        List<Journey> list = null;
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        transaction.begin(journeyDAO, userDAO, catalogDAO);
        logger.debug(String.format("today : %s time %s", LocalDate.now(), LocalTime.now()));

        try {
            list = journeyDAO.takeListOfNearestTripForMain(LocalDate.now(), LocalTime.now(), limitFrom, limitTo);
            for (Journey journey : list) {
                User user = userDAO.findEntityById(journey.getDriver().getId());
                journey.getDriver().setId(user.getId());
                journey.getDriver().setName(user.getName());
                journey.setStartAddress(catalogDAO.getAddressByCityId(
                        Integer.parseInt(journey.getStartAddress().getCity())));
                journey.setDestinationAddress(catalogDAO.getAddressByCityId(
                        Integer.parseInt(journey
                                .getDestinationAddress().getCity())));
                journey.setCurrency(catalogDAO.getCurrency(Integer.parseInt(journey.getCurrency())));
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

    @Override
    public Journey findJourney(int id) {
        Journey journey = null;
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        transaction.begin(journeyDAO, userDAO, catalogDAO);
        try {
            journey = journeyDAO.findEntityById(id);
            logger.debug(String.format("journey driver id: %s", journey));
            logger.debug(String.format("journey driver id: %s", journey.getDriver().getId()));
            User driverInfo = userDAO.findEntityById(journey.getDriver().getId());
            journey.setDriver(driverInfo);
            journey.setStartAddress(catalogDAO.getAddressByCityId(Integer.parseInt(journey.getStartAddress().getCity())));
            journey.setDestinationAddress(catalogDAO.getAddressByCityId(Integer.parseInt(journey.getDestinationAddress().getCity())));
            journey.setCurrency(catalogDAO.getCurrency(Integer.parseInt(journey.getCurrency())));

            if (journey.getDriver().getCar() != null) {
                Car car = catalogDAO.getCar(Integer.parseInt(
                        journey.getDriver().getCar().getModel()));
                car.setYearOfProduce(journey.getDriver().getCar().getYearOfProduce());
                car.setAirConditioner(catalogDAO
                        .getClimateType(Integer.parseInt(journey.getDriver()
                                .getCar().getAirConditioner())));
                journey.getDriver().setCar(car);
            }
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
        } finally {
            transaction.end();
        }

        return journey;
    }
}
