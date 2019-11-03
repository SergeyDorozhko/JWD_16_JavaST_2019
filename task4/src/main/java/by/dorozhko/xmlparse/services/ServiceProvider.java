package by.dorozhko.xmlparse.services;

import by.dorozhko.xmlparse.services.impl.ServiceImpl;

public final class ServiceProvider {
    /**
     * single tone.
     */
    private static final ServiceProvider INSTANCE = new ServiceProvider();

    private ServiceProvider() {
    }

    /**
     * link to implementation.
     */
    private Service service = new ServiceImpl();

    /**
     * get instance of this class.
     * @return link
     */
    public static ServiceProvider getInstance() {
        return INSTANCE;
    }

    /**
     * get service.
     * @return link to service.
     */
    public Service getService() {
        return service;
    }
}
