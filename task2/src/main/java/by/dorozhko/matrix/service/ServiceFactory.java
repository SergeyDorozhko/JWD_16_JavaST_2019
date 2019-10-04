package by.dorozhko.matrix.service;

import by.dorozhko.matrix.service.impl.Service;

public final class ServiceFactory {
    /**
     * single tone.
     */
    private static final ServiceFactory instance = new ServiceFactory();
    /**
     * link to matrix services.
     */
    private MatrixService service = new Service();

    private ServiceFactory() {
    }

    /**
     * get link to single object of factory.
     *
     * @return link.
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * take service.
     * @return link to service.
     */
    public MatrixService getService() {
        return service;
    }

}
