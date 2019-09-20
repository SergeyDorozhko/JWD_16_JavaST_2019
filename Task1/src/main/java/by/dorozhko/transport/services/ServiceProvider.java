package by.dorozhko.transport.services;

import by.dorozhko.transport.dal.exception.DALException;
import by.dorozhko.transport.services.impl.ImplServices;

public class ServiceProvider {

    private final static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    private Services service = new ImplServices();

    public static ServiceProvider getInstance() {
        return instance;
    }

    public Services getService() {
        return service;
    }
}
