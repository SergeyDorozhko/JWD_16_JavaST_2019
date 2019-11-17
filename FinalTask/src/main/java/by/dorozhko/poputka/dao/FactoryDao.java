package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.impl.MySqlUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FactoryDao {
    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private static final FactoryDao instance = new FactoryDao();



    private UserDAO userDAO = new MySqlUserDao();

    private FactoryDao(){}

    public static FactoryDao getInstance(){
        return instance;
    }


    public UserDAO getUserDAO(){
        return userDAO;
    }

}
