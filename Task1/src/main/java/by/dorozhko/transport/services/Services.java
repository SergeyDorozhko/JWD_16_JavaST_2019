package by.dorozhko.transport.services;

import by.dorozhko.transport.services.exception.ServiceException;


public interface Services {
    public boolean addEntityFromData() throws ServiceException;

    public boolean addEntity(String data);

    public String deleteEntity(String id);

    public String viewByQuery(String query);

    public String countPassengers(String keyToCarriageAccess);

    public String countBaggage(String keyToCarriageAccess);

    public String viewCarriagesWhereNumberOfPassengersBetween(String param);


}
