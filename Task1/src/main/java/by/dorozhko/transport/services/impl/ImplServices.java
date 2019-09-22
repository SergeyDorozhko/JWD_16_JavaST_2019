package by.dorozhko.transport.services.impl;

import by.dorozhko.transport.dal.exception.DALException;
import by.dorozhko.transport.dal.FactoryDAL;
import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.Train;
import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.entity.params.CarriageType;
import by.dorozhko.transport.entity.params.EngineType;
import by.dorozhko.transport.repository.RepositoryFactory;
import by.dorozhko.transport.repository.SpecificationProvider;
import by.dorozhko.transport.repository.TransportRepository;
import by.dorozhko.transport.services.Services;
import by.dorozhko.transport.services.exception.ServiceException;
import by.dorozhko.transport.services.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ImplServices implements Services {
    /**
     * logger.
     */
    private final Logger logger =
            LogManager.getLogger(ImplServices.class.getName());

    /**
     * Method remove entity from repository bu id.
     *
     * @param line number of line to delete
     * @return result.
     */
    public String deleteEntity(final String line) {

        logger.trace("Serv start delete line.");

        int removeId = Integer.parseInt(line.trim());

        logger.trace(removeId);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        TransportRepository transportRepository =
                repositoryFactory.getRepository();
        String result = "";
        try {
            result = transportRepository.delete(removeId);
            result = result + " from line " + line
                    + " -  successfully removed.";
        } catch (Exception e) {
            logger.error("line not found", e);
            result = "Line №" + line + "don't found.";
        }
        logger.trace(result);
        return result;
    }

    /**
     * Query to repository.
     *
     * @param query query.
     * @return result.
     */
    @Override
    public String viewByQuery(final String query) {
        logger.debug(query);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        TransportRepository transportRepository
                = repositoryFactory.getRepository();

        SpecificationProvider specification
                = SpecificationProvider.getInstance();
        List<TransportEntity> list = transportRepository.
                query(specification.getSpecification(query));


        StringBuilder result = new StringBuilder("");
        int lineNumber = 0;
        for (TransportEntity entity : list) {

            result.append(lineNumber + ": " + entity.toString() + "\n");
            lineNumber++;
        }
        return result.toString();
    }

    /**
     * Method take carriages from storage and count all passengers.
     *
     * @param keyToPassengers key to take all carriages.
     * @return number of passengers.
     */
    @Override
    public String countPassengers(final String keyToPassengers) {

        logger.debug("countPassengers");
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        TransportRepository transportRepository =
                repositoryFactory.getRepository();

        SpecificationProvider specification =
                SpecificationProvider.getInstance();
        List<TransportEntity> list = transportRepository.
                query(specification.getSpecification(keyToPassengers));


        int numberOfPassegers = 0;
        for (TransportEntity entity : list) {
            Carriage carriage = (Carriage) entity;
            numberOfPassegers += carriage.getNumberOfPassengers();

        }

        return "" + numberOfPassegers;
    }


    /**
     * Method take carriages from storage and count all baggage.
     * @param keyToCarriageAccess key to take all carriages.
     * @return number of baggage.
     */
    @Override
    public String countBaggage(final String keyToCarriageAccess) {
        logger.debug("start count baggage");

        RepositoryFactory repository = RepositoryFactory.getInstance();
        TransportRepository transportRepository = repository.getRepository();

        SpecificationProvider provider = SpecificationProvider.getInstance();
        List<TransportEntity> list = transportRepository.
                query(provider.getSpecification(keyToCarriageAccess));

        int countBaggageResult = 0;

        for (TransportEntity entity : list) {
            countBaggageResult += ((Carriage) entity).getMaxValueOfBaggage();
        }
        logger.debug(countBaggageResult);
        return "" + countBaggageResult;
    }


    /**
     * Method take carriages from storage
     * where number of passengers between x - y.
     * @param param param fo found carriages.
     * @return list f carriages.
     */
    @Override
    public String
    viewCarriagesWhereNumberOfPassengersBetween(final String param) {
        logger.trace(param);

        String[] specificationThenMinMaxValue = param.split("-");

        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        TransportRepository transportRepository
                = repositoryFactory.getRepository();

        SpecificationProvider specification
                = SpecificationProvider.getInstance();

        List<TransportEntity> list =
                transportRepository.query(specification.
                        getSpecification(specificationThenMinMaxValue[0]));

        logger.trace(list.size());

        boolean isMinNumber = Validator.
                isNumber(specificationThenMinMaxValue[1].split("to")[0]);

        int minValue = -1;
        if (isMinNumber) {
            minValue = Integer.valueOf(specificationThenMinMaxValue[1].
                    split("to")[0]);
        }

        logger.trace("" + minValue + isMinNumber);


        boolean isMaxNumber
                = Validator.isNumber(specificationThenMinMaxValue[1].
                split("to")[1]);

        int maxValue = -1;
        if (isMaxNumber) {
            maxValue = Integer.valueOf(specificationThenMinMaxValue[1].
                    split("to")[1]);
        }

        logger.trace("" + maxValue + isMaxNumber);


        if (minValue == -1 || maxValue == -1 || minValue > maxValue) {
            return "Incorrect input. (Number of passengers.)";
        }

        List<TransportEntity> resultList = new ArrayList<>();

        for (TransportEntity entity : list) {
            Carriage carriage = (Carriage) entity;
            logger.trace("in foreach");
            if (carriage.getNumberOfPassengers() >= minValue
                    && carriage.getNumberOfPassengers() <= maxValue) {
                resultList.add(carriage);
            }
        }

        StringBuilder result = new StringBuilder("");
        int lineNumber = 0;
        for (TransportEntity entity : resultList) {

            result.append(lineNumber + ": " + entity.toString() + "\n");
            lineNumber++;
        }

        return result.toString();

    }

    /**
     * Method add new entities to memory from data.
     *
     * @return boolean is successful.
     */
    @Override
    public boolean addEntityFromData() throws ServiceException {
        FactoryDAL readData = FactoryDAL.getInstance();
        List<String> data;
        try {
            data = readData.getTransportDAO().read();
        } catch (DALException e) {
            logger.error("access to data:", e);
            throw new ServiceException(e);
        }


        for (String params : data) {
            addEntity(params);
        }
        return true;
    }

    /**
     * Method add new entity to memory.
     *
     * @param data entity params.
     * @return boolean is successful.
     */
    @Override
    public boolean addEntity(final String data) {
        boolean result = false;
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        TransportRepository repository = repositoryFactory.getRepository();

        if (Validator.isValidLine(data)) {
            result = repository.add(createEntity(data));
        }

        return result;
    }

    private TransportEntity createEntity(final String data) {
        String[] params = data.split(":");

        boolean isCarriage = params[0].trim()
                .equals(Carriage.class.getSimpleName());

        TransportEntity entity;
        if (isCarriage) {
            entity = createCarriage(params[1]);
        } else {
            entity = createTrain(params[1]);
        }
        return entity;
    }

    private Carriage createCarriage(final String params) {
        String[] param = params.split(",");
        String name = param[0].split("=")[1].trim();
        int weight = Integer.parseInt(param[1].split("=")[1].trim());
        int length = Integer.parseInt(param[2].split("=")[1].trim());
        CarriageType type = CarriageType.valueOf(param[3].split("=")[1].trim());
        int passengersNumber = Integer.parseInt(param[4].split("=")[1].trim());
        int baggageValue = Integer.parseInt(param[5].split("=")[1].trim());

        return new Carriage(name, weight,
                length, type, passengersNumber, baggageValue);
    }

    private Train createTrain(final String params) {
        String[] param = params.split(",");


        String name = param[0].split("=")[1].trim();
        int weight = Integer.parseInt(param[1].split("=")[1].trim());
        int length = Integer.parseInt(param[2].split("=")[1].trim());
        EngineType type = EngineType.valueOf(param[3].split("=")[1].trim());
        int horsePower = Integer.parseInt(param[4].split("=")[1].trim());
        int maxSpeed = Integer.parseInt(param[5].split("=")[1].trim());

        return new Train(name, weight, length, type, horsePower, maxSpeed);
    }
}
