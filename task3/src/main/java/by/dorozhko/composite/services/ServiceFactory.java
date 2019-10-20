package by.dorozhko.composite.services;

import by.dorozhko.composite.services.impl.CompositeService;

public final class ServiceFactory {
    /**
     * single tone.
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    /**
     * single access to realization.
     */
    private Service service = new CompositeService();

    private ServiceFactory() { }

    /**
     * link to object of class.
     * @return link.
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * get ling to realisation of service.
     * @return link.
     */
    public Service getService() {
        return service;
    }
}
