package by.dorozhko.transport.services;

import by.dorozhko.transport.services.exception.ServiceException;


public interface Services {
    /**
     * Abstract method to read data from data and  create entity.
     *
     * @return result of action.
     * @throws ServiceException cover DAL exception.
     */
    boolean addEntityFromData() throws ServiceException;

    /**
     * Method add Entity to tne repository.
     *
     * @param data entity params.
     * @return result of action.
     */
    boolean addEntity(String data);

    /**
     * Method remove entity from repository.
     *
     * @param line number of line of entity.
     * @return type of deleted entity.
     */
    String deleteEntity(String line);

    /**
     * Method show information by query.
     *
     * @param query query params.
     * @return information by query.
     */
    String viewByQuery(String query);

    /**
     * Method show information by query.
     *
     * @param keyToCarriageAccess key to access to the storage and action.
     * @return number of passengers
     */
    String countPassengers(String keyToCarriageAccess);

    /**
     * Method show information by query.
     *
     * @param keyToCarriageAccess key to access to the storage and action.
     * @return number of baggage positions.
     */
    String countBaggage(String keyToCarriageAccess);

    /**
     * Method show information by query.
     *
     * @param param find between.
     * @return all carriages which have current number of passengers.
     */
    String viewCarriagesWhereNumberOfPassengersBetween(String param);


}
