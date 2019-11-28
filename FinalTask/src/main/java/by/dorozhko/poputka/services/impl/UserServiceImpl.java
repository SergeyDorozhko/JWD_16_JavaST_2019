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

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Formatter;
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
            String salt = userDAO.getSalt(login);
            if (salt != null) {
                HashingPBKDF2 hashingPBKDF2 = new HashingPBKDF2();
                hashingPBKDF2.setSalt(salt);
                String passwordHash = hashingPBKDF2.generatePwdHash(password);
                user = userDAO.findUserByLoginAndPassword(login, passwordHash);
            }
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


    private class HashingPBKDF2 {


        private SecureRandom random;
        private byte[] salt;
        private KeySpec spec;
        private SecretKeyFactory secretKeyFactory;
        private byte[] hash;

        private HashingPBKDF2() {
            random = new SecureRandom();
            salt = new byte[16];
            try {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }


        public String generateSalt() {
            System.out.println("salt - " + Arrays.toString(salt));
            random.nextBytes(salt);
            System.out.println("salt after - " + Arrays.toString(salt));

            Formatter formatter = new Formatter();
            for (int i = 0; i < salt.length; i++) {
                formatter.format("%02X", salt[i]);
            }
            String hashSalt = formatter.toString();
            System.out.println("salt hash -> " + hashSalt);
            return hashSalt;
        }

        public String generatePwdHash(String pwd) {
            spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
            System.out.println(pwd + " -> " + Arrays.toString(hash));

            try {
                hash = secretKeyFactory.generateSecret(spec).getEncoded();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            System.out.println(pwd + " -> " + Arrays.toString(hash));

            Formatter formatter = new Formatter();
            for (int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String hashPBKDF2 = formatter.toString();
            System.out.println(pwd + " hash -> " + hashPBKDF2);
            return hashPBKDF2;
        }

        public void setSalt(String newSalt) {
            byte[] val = new byte[newSalt.length() / 2];
            for (int i = 0; i < val.length; i++) {
                int index = i * 2;
                int j = Integer.parseInt(newSalt.substring(index, index + 2), 16);
                val[i] = (byte) j;
            }

            salt = val;
        }

    }
}
