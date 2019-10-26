package by.dorozhko.xmlparse.services;

import by.dorozhko.xmlparse.services.impl.ServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instace = new ServiceProvider();

    private ServiceProvider() {
    }

    private Service service = new ServiceImpl();

    public static ServiceProvider getInstance() {
        return instace;
    }

    public Service getService() {
        return service;
    }
}
