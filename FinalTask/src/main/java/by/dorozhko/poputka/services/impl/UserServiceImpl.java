package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.FactoryDao;
import by.dorozhko.poputka.dao.Transaction;
import by.dorozhko.poputka.dao.TransactionFactory;
import by.dorozhko.poputka.dao.UserDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Show all users saved in database.
     *
     * @return all users.
     */
    @Override
    public List<User> findAll() {
        List<User> userList = null;
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(userDAO);
        try {
            userList = userDAO.findAll();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            System.out.println(exceptionDao);
            transaction.rollback();
        }
        transaction.end();

        return userList;
    }

    /**
     * Find user by id method.
     *
     * @param id identity of user.
     * @return user if find.
     */
    @Override
    public User findById(int id) {
        return null;
    }

    /**
     * Method try to login in app, checking user login and password.
     *
     * @param login    User login.
     * @param password User password.
     * @return User with user params if find one.
     */
    @Override
    public User singIn(final String login,
                       final String password)
            throws ExceptionService {
        Transaction transaction = TransactionFactory
                .getInstance().getTransaction();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();

        transaction.begin(userDAO);

        User user = null;
        try {
            user = userDAO.findUserByLoginAndPassword(login, password);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
            throw new ExceptionService(exceptionDao);
        } finally {
            transaction.end();
        }


        return user;

    }

    /**
     * Save or update (if user exist) user info into database.
     *
     * @param user User.
     * @return true if successfully saved, otherwise false.
     */
    @Override
    public boolean add(User user) {
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();

        transaction.begin(userDAO);
        boolean result = false;
        try {
            userDAO.create(user);
            transaction.commit();
            result = true;
        } catch (ExceptionDao exceptionDao) {
            System.out.println(exceptionDao);
            transaction.rollback();
            result = false;
        }

        transaction.end();

        return result;
    }

    /**
     * Update user info into database.
     *
     * @param user User.
     * @return true if successfully saved, otherwise false.
     */
    @Override
    public boolean update(User user) {
        return false;
    }

    /**
     * Delete user by identity.
     *
     * @param id Identity of user.
     * @return true if successfully deleted, otherwise false.
     */
    @Override
    public boolean delete(int id) {
        return false;
    }
}
