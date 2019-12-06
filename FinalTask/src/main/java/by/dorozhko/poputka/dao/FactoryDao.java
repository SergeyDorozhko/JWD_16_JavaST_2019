package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.mysql.MySqlCatalogDAO;
import by.dorozhko.poputka.dao.mysql.MySqlJourneyDAO;
import by.dorozhko.poputka.dao.mysql.MySqlUserDao;


public final class FactoryDao {
    /**
     * static variable single_instance of type FactoryDao.
     */
    private static final FactoryDao INSTANCE = new FactoryDao();


    /**
     * Cunstructing
     */
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

    public JourneyDAO getJourneyDAO() {
        return new MySqlJourneyDAO();
    }

    public CatalogDAO getCatalogDAO() {
        return new MySqlCatalogDAO();
    }


}
