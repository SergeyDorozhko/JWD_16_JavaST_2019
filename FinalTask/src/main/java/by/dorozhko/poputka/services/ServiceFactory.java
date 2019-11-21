package by.dorozhko.poputka.services;

import by.dorozhko.poputka.services.impl.ConnectionServiceImpl;
import by.dorozhko.poputka.services.impl.UserServiceImpl;

public final class ServiceFactory {
    /**
     * static variable single_instance of type FactoryDao.
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private ConnectionService connectionService = new ConnectionServiceImpl();

    private ServiceFactory() {

    }


    /**
     * Access to single instance of type ServiceFactory.
     *
     * @return link to instance of class.
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Take access to userService. By every query generates
     * new instance.
     *
     * @return link to userService.
     */
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public ConnectionService getConnectionService() {
        return connectionService;
    }
}
