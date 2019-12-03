package by.dorozhko.poputka.services;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.exception.ExceptionService;

import java.util.List;

public interface UserService extends Service {
    /**
     * Show all users saved in database.
     * @return all users.
     */
    List<User> findAll();

    /**
     * Find user by id method.
     * @param id identity of user.
     * @return user if find.
     */
    User findById(int id) throws ExceptionService;

    /**
     * Method try to login in app, checking user login and password.
     * @param login User login.
     * @param password User password.
     * @return User with user params if find one.
     */
    User singIn(String login, String password) throws ExceptionService;

    /**
     * Save user info into database.
     * @param user User.
     * @return true if successfully saved, otherwise false.
     */
    User add(User user) throws ExceptionService;
    /**
     * Update user info into database.
     * @param user User .
     * @return true if successfully saved, otherwise false.
     */
    User update(User user) throws ExceptionService;


    User takeDataForEditProfile(int id) throws ExceptionService;
    User addCar(User user) throws ExceptionService;
    User deleteCar(User user) throws ExceptionService;
    boolean deleteUser(User user) throws ExceptionService;
}
