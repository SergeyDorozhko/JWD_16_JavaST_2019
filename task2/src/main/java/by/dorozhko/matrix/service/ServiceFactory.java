package by.dorozhko.matrix.service;

import by.dorozhko.matrix.service.impl.Service;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private MatrixService service = new Service();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public MatrixService getService() {
        return service;
    }

}
