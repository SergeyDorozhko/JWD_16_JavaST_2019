package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.connection.ConnectionPool;
import by.dorozhko.poputka.dao.mysql.MySqlUserDao;


public final class FactoryDao {
    /**
     * static variable single_instance of type FactoryDao.
     */
    private static final FactoryDao INSTANCE = new FactoryDao();

    //TODO may be need in any case make new obgect of USERDAO?

    /**
     * Cunstructing
     */
    // TODO private UserDAO userDAO = new MySqlUserDao();
    private FactoryDao() {
    }

    /**
     * Access to single instance of type FactoryDao.
     *
     * @return link to instance of class.
     */
    public static FactoryDao getInstance() {
        return INSTANCE;
    }

    /**
     * Take access to userDAO.
     *
     * @return link to userDao.
     */
    public UserDAO getUserDAO() {
        return new MySqlUserDao();
    }

    public void initConnectionPool() {
        ConnectionPool.getInstance();
    }

    public void closeConnectionPool() {
        ConnectionPool.getInstance().closePool();
    }
}
