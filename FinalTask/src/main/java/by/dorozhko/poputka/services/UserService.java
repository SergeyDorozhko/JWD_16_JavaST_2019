package by.dorozhko.poputka.services;

import by.dorozhko.poputka.entity.User;

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
    User findById(int id);

    /**
     * Method try to login in app, checking user login and password.
     * @param login User login.
     * @param password User password.
     * @return User with user params if find one.
     */
    User singIn(String login, String password);

    /**
     * Save user info into database.
     * @param user User.
     * @return true if successfully saved, otherwise false.
     */
    boolean add(User user);
    /**
     * Update user info into database.
     * @param user User.
     * @return true if successfully saved, otherwise false.
     */
    boolean update(User user);
    /**
     * Delete user by identity.
     * @param id Identity of user.
     * @return true if successfully deleted, otherwise false.
     */
    boolean delete(int id);
}
