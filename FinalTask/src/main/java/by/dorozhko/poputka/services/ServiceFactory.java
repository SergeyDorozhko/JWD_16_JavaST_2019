package by.dorozhko.poputka.services;

import by.dorozhko.poputka.services.impl.UserServiceImpl;

public class ServiceFactory {

    public UserService getUserService(){
        return new UserServiceImpl();
    }
}
