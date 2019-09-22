package by.dorozhko.transport.services;

import by.dorozhko.transport.services.impl.ImplServices;

public final class ServiceProvider {
    /**
     * Singletone.
     */
    private static final ServiceProvider instance = new ServiceProvider();

    private ServiceProvider() {
    }

    /**
     * Service creator.
     */
    private Services service = new ImplServices();

    /**
     * Static method to get single provider.
     * @return Service provider.
     */
    public static ServiceProvider getInstance() {
        return instance;
    }

    /**
     * Connecting to the sercive.
     * @return servise.
     */
    public Services getService() {
        return service;
    }
}
