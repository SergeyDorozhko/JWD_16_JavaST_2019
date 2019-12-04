package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.AbstractService;
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


public class UserServiceImpl extends AbstractService implements UserService {
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
    public User findById(int id) throws ExceptionService {

        User userInfo = null;
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();

        transaction.begin(userDAO, catalogDAO);
        try {
            boolean hasCar = userDAO.hasUserCar(id);
            if (hasCar) {
                logger.debug("user has car");
                userInfo = userDAO.findUserInfoWithCar(id);
                Car car = catalogDAO.getCar(Integer.parseInt(
                        userInfo.getCar().getModel()));
                userInfo.getCar().setBrand(car.getBrand());
                userInfo.getCar().setModel(car.getModel());
                userInfo.getCar().setAirConditioner(catalogDAO
                        .getClimateType(Integer.parseInt(userInfo
                                .getCar().getAirConditioner())));
            } else {
                logger.debug("user without car");
                userInfo = userDAO.findUserInfoWithoutCar(id);
            }
            userInfo.setGender(catalogDAO.getGender(Integer.parseInt(userInfo.getGender())));
            userInfo.setCountry(catalogDAO.getCountry(Integer.parseInt(userInfo.getCountry())));

            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
            throw new ExceptionService(exceptionDao);
        } finally {
            transaction.end();
        }
        return userInfo;
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
        Validator validator = new Validator();
        if (!validator.validateAutorisationData(login, password)) {
            String msg = "incorrect login or password";
            logger.error(msg);
            throw new ExceptionService(msg);
        }

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
    public User add(final User user) throws ExceptionService {

        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();

        transaction.begin(userDAO);

        HashingPBKDF2 hashingPBKDF2 = new HashingPBKDF2();
        user.setSalt(hashingPBKDF2.generateSalt());
        String passwordHash = hashingPBKDF2
                .generatePwdHash(user.getPassword());
        user.setPassword(passwordHash);

        User regestedUser = null;
        try {
            regestedUser = userDAO.create(user);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            System.out.println(exceptionDao);
            transaction.rollback();
            throw new ExceptionService(exceptionDao);
        }

        transaction.end();

        return regestedUser;
    }

    /**
     * Update user info into database.
     *
     * @param user User .
     * @return true if successfully saved, otherwise false.
     */
    @Override
    public User update(User user) throws ExceptionService {
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        transaction.begin(userDAO);

        try {
            user = userDAO.update(user);
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

    @Override
    public User takeDataForEditProfile(int id) throws ExceptionService {

        User user = null;
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        transaction.begin(userDAO);

        try {
            user = userDAO.findUserInfoWithoutCar(id);
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            throw new ExceptionService(exceptionDao);
        }
        return user;
    }


    @Override
    public User addCar(User user) throws ExceptionService {
        User userInfo = null;
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();

        transaction.begin(userDAO);
        try {
            userInfo = userDAO.addCar(user);

            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
            throw new ExceptionService(exceptionDao);
        } finally {
            transaction.end();
        }
        return userInfo;
    }

    @Override
    public User deleteCar(User user) throws ExceptionService {
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        transaction.begin(userDAO);

        try {
            userDAO.deleteCar(user);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
            throw new ExceptionService(exceptionDao);
        }

        return user;
    }

    @Override
    public boolean deleteUser(User user) throws ExceptionService {
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        singIn(user.getLogin(), user.getPassword());
        transaction.begin(userDAO);

        try {

            userDAO.delete(user.getId());
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
        } finally {
            transaction.end();
        }

        return true;
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
