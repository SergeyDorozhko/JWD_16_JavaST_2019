package by.dorozhko.composite.services;

import by.dorozhko.composite.services.impl.CompositeService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private Service service = new CompositeService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Service getService() {
        return service;
    }
}
