package by.dorozhko.composite.services;

import by.dorozhko.composite.services.impl.CompositeService;

public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private Service service = new CompositeService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public Service getService() {
        return service;
    }
}
