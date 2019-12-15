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
    public static final String INVALID_FORMAT_OF_START_COUNTRY = "invalid format of start country";
    public static final String INVALID_FORMAT_OF_START_REGION = "invalid format of start region";
    public static final String INVALID_FORMAT_OF_START_CITY = "invalid format of start city";
    public static final String INVALID_FORMAT_OF_DESTINATION_COUNTRY = "invalid format of destination country";
    public static final String INVALID_FORMAT_OF_DESTINATION_REGION = "invalid format of destination region";
    public static final String INVALID_FORMAT_OF_DESTINATION_CITY = "invalid format of destination city";
    public static final String INVALID_FORMAT_OF_DATE = "invalid format of date";
    public static final String INVALID_TIME_FORMAT = "invalid time format";
    public static final String INVALID_COST_VALUE = "invalid cost value";
    public static final String INVALID_FORMAT_OF_CURRENCY = "invalid format of currency";
    public static final String INVALID_NUMBER_OF_PASSENGERS = "invalid number of passengers";
    public static final String INVALID_CHARACTERS_IN_COMMENTARY = "invalid characters in commentary";
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Validator validator;

    public JourneyServiceImpl() {
        validator = new Validator();
    }

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
        validateJourney(journey);
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
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

    private void validateJourney(Journey journey) throws ExceptionService {
        logger.debug(journey);
        validateWithIdField(journey.getStartAddress().getCountry(), INVALID_FORMAT_OF_START_COUNTRY);
        validateWithIdField(journey.getStartAddress().getRegionalCenter(), INVALID_FORMAT_OF_START_REGION);
        validateWithIdField(journey.getStartAddress().getCity(), INVALID_FORMAT_OF_START_CITY);
        validateWithIdField(journey.getDestinationAddress().getCountry(), INVALID_FORMAT_OF_DESTINATION_COUNTRY);
        validateWithIdField(journey.getDestinationAddress().getRegionalCenter(), INVALID_FORMAT_OF_DESTINATION_REGION);
        validateWithIdField(journey.getDestinationAddress().getCity(), INVALID_FORMAT_OF_DESTINATION_CITY);
        final int todayDate = -2;
        final int afterTwoYear = 0;
        if (!validator.validateDate(journey.getDepartureDate(), todayDate, afterTwoYear)) {
            throw new ExceptionService(INVALID_FORMAT_OF_DATE);
        }
        if (!validator.validateFutureTimeByDate(journey.getDepartureDate(), journey.getDepartureTime())) {
            throw new ExceptionService(INVALID_TIME_FORMAT);
        }
        if (!validator.validateCost(journey.getCost())) {
            throw new ExceptionService(INVALID_COST_VALUE);
        }
        validateWithIdField(journey.getCurrency(), INVALID_FORMAT_OF_CURRENCY);
        if (!validator.validateForPositiveInteger(journey.getPassengersNumber())) {
            throw new ExceptionService(INVALID_NUMBER_OF_PASSENGERS);
        }
        if (!validator.validateCommentary(journey.getAdditionalInformation())) {
            throw new ExceptionService(INVALID_CHARACTERS_IN_COMMENTARY);
        }
    }

    private void validateWithIdField(String idValue, String errorMessage) throws ExceptionService {
        if (!validator.validateForPositiveInteger(Integer.parseInt(idValue))) {
            throw new ExceptionService(errorMessage);
        }
    }

    @Override
    public Journey updateJourney(Journey journey) throws ExceptionService {
        validateJourney(journey);
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        transaction.begin(journeyDAO, userDAO);

        try {
            int userInfoId = userDAO.findUserInfoIdByUsersId(journey.getDriver().getId());
            journey.getDriver().setId(userInfoId);

            journey = journeyDAO.update(journey);
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

    @Override
    public Journey findJourney(int journeyId, int driverId) {
        Journey journey = null;
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        JourneyDAO journeyDAO = FactoryDao.getInstance().getJourneyDAO();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        transaction.begin(userDAO, journeyDAO, catalogDAO);

        try {
            int userInfoId = userDAO.findUserInfoIdByUsersId(driverId);
            journey = journeyDAO.takeJourneyByJourneyIdAndDriverId(journeyId, userInfoId);

            if (journey != null) {
                journey.setStartAddress(catalogDAO.getAddressIdByCityId(
                        Integer.parseInt(journey.getStartAddress().getCity())));
                journey.setDestinationAddress(catalogDAO.getAddressIdByCityId(
                        Integer.parseInt(journey.getDestinationAddress().getCity())));
            }
            transaction.commit();

        } catch (ExceptionDao ex) {
            transaction.rollback();
        } finally {
            transaction.end();
        }
        return journey;
    }
}
