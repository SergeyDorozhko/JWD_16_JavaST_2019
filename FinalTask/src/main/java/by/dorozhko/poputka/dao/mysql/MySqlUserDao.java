package by.dorozhko.poputka.dao.mysql;


import by.dorozhko.poputka.dao.UserDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDAO {
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Logger of this class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * Query to mySQL database to find all users, return table with identity
     * and login of users.
     */
    //TODO optimize query
    private static final String SELECT_ALL_USERS
            = "SELECT users.id, users.login from users";

    //TODO optimize query
    private static final String SELECT_ALL_USER_INFO
            = "SELECT user_info.id, users.login, user_info.surname, user_info.name, user_info.birthday, user_info.gender, user_info.country, user_info.passport_number, user_info.passport_date_of_issue, user_info.phone, user_info.email, user_info.car_id, user_info.driving_experience_since from user_info inner join users ON user_info.user_id = users.id";
    /**
     * Query to mySQL database to insert new user to users database table.
     */
    private static final String INSERT_INTO_USERS
            = "INSERT INTO users (login, password, role) values (?,?,?)";
    /**
     * Query to mySQL database to find identity of new user, return identity
     * of user.
     */
    private static final String SELECT_ID_NEW_USER
            = "SELECT id FROM users WHERE login=?";
    /**
     * Query to mySQL database to insert new user to user_info database table.
     */
    private static final String INSERT_INTO_USER_INFO
            = "INSERT INTO user_info(user_id, surname, name, birthday, gender,"
            + "country, passport_number, passport_date_of_issue, phone, email,"
            + " car_id, driving_experience_since)"
            + " values (?,?,?,?,?,?,?,?,?,?,?,?)";


    /**
     * Method take connection to database and set it to realisation of Dao.
     *
     * @param newConnection - connection to database.
     */
    @Override
    public void setConnection(final Connection newConnection) {
        connection = newConnection;
    }

    /**
     * Save new user to database.
     *
     * @param entity - user which will saved to database.
     * @return - result of saving. return true value if successfully.
     * @throws ExceptionDao - generating if some values conflicts
     *                      with existed in database.
     */
    @Override
    public boolean create(final User entity) throws ExceptionDao {

        try (PreparedStatement statement
                     = connection.prepareStatement(INSERT_INTO_USERS);
             PreparedStatement statementRequest
                     = connection.prepareStatement(SELECT_ID_NEW_USER);
             PreparedStatement statementUserInf
                     = connection.prepareStatement(INSERT_INTO_USER_INFO);) {

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole());
            statement.executeUpdate();

            statementRequest.setString(1, entity.getLogin());
            ResultSet resultSet = statementRequest.executeQuery();
            int userId = 0;
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }

            statementUserInf.setInt(1, userId);
            statementUserInf.setString(2, entity.getSurname());
            statementUserInf.setString(3, entity.getName());
            statementUserInf.setString(4, entity.getBirthday().toString());
            statementUserInf.setByte(5, entity.getGender());
            statementUserInf.setString(6, entity.getCountry());
            statementUserInf.setString(7, entity.getPassportNumber());
            statementUserInf.setString(8, entity.getPassportDateOfIssue());
            statementUserInf.setString(9, entity.getPhoneNumber());
            statementUserInf.setString(10, entity.getEmail());
            //TODO car
            statementUserInf.setString(11, null);
            statementUserInf.setString(12, null);
            statementUserInf.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }
        return true;
    }

    /**
     * Remove user from database.
     *
     * @param id - identity of removing user.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(final Integer id) {
        return false;
    }

    /**
     * Remove user from database.
     *
     * @param entity - user which needed to be removed.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(final User entity) {
        return false;
    }

    /**
     * Update user values.
     *
     * @param entity - user which needed to be update
     *               with new params.
     * @return - user with new params.
     */
    @Override
    public User update(final User entity) {
        return null;
    }

    /**
     * Find all users from database.
     *
     * @return - list of users which were found.
     * @throws ExceptionDao - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public List<User> findAll() throws ExceptionDao {
        List<User> list = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL_USERS);) {
            while (resultSet.next()) {
                User user = new User();

                user.setLogin(resultSet.getString("login"));
                user.setId(Integer.parseInt(resultSet.getString("id")));
                list.add(user);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return list;
    }

    /**
     * Find user from database by identity.
     *
     * @param id - identity of target user.
     * @return - entity which corresponds to identity.
     */
    @Override
    public User findEntityById(final Integer id) {
        //TODO realisation
        return null;
    }
}
