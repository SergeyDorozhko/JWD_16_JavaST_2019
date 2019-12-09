package by.dorozhko.poputka.dao.mysql;


import by.dorozhko.poputka.dao.UserDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
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
    private static final String SELECT_ALL_USERS
            = "SELECT users.id, users.login from users";

    private static final String SELECT_USER_FROM_TRIP_BY_ID
            = " SELECT user_info.user_id, user_info.name, user_info.email, user_info.phone, brand_and_model_id, year_of_produce, climate_type_id  FROM user_info LEFT JOIN cars  on user_info.car_id = cars.id"
            + " WHERE user_info.id = ?;";

    private static final String SELECT_ID_LOGIN_ROLE_BY_ID
            = " SELECT users.login, users.role, users.id FROM users"
            + " WHERE id = ?;";

    private static final String SELECT_SALT_BY_LOGIN
            = "SELECT salt FROM users"
            + " WHERE login = ?;";


    private static final String SELECT_USER_BY_LOGIN_PWD
            = "SELECT id, login, role FROM users"
            + " WHERE login = ? and password = ?;";
    /**
     * Query to mySQL database to insert new user to users database table.
     */
    private static final String INSERT_INTO_USERS
            = "INSERT INTO users (login, password, salt) values (?,?,?)";

    /**
     * Query to mySQL database to insert new user to user_info database table.
     */
    private static final String INSERT_INTO_USER_INFO
            = "INSERT INTO user_info(user_id, surname, name, birthday, gender_id,"
            + "country_id, passport_number, passport_date_of_issue, phone, email)"
            + " values (?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_INTO_CARS
            = "INSERT INTO cars (brand_and_model_id, year_of_produce,"
            + "climate_type_id) values (?,?,?)";
    private static final String UPDATE_CAR_INTO_USER_INFO
            = "UPDATE user_info SET car_id = ? WHERE user_id = ?;";
    private static final String UPDATE_USER_INFO
            = "UPDATE user_info, users SET users.login = ?, name = ?,"
            + " surname = ?, email = ?, birthday = ?, phone = ?,"
            + " country_id = ?, passport_number = ?, passport_date_of_issue = ?,"
            + " gender_id = ?  WHERE users.id = ? AND user_info.user_id = ?;";
    private static final String SELECT_ALL_USER_INFO_WITH_CAR_BY_ID
            = " SELECT login, surname, name, birthday, gender_id, country_id,"
            + " passport_number, passport_date_of_issue, phone, email, brand_and_model_id, year_of_produce, climate_type_id"
            + " from cars, users, user_info"
            + " WHERE user_info.user_id = ? "
            + " AND user_info.user_id = users.id"
            + " AND user_info.car_id =cars.id";


    private static final String SELECT_ALL_USER_INFO_WITHOUT_CAR_BY_ID
            = " SELECT login, surname, name, birthday, gender_id, country_id,"
            + " passport_number, passport_date_of_issue, phone, email"
            + " from users, user_info"
            + " WHERE user_info.user_id = ? "
            + " AND user_info.user_id = users.id";

    private static final String CHECK_HAS_USER_CAR =
            "SELECT user_id,"
                    + " IFNULL(car_id, 'no car') AS car"
                    + " FROM user_info"
                    + " where user_id = ?";
    private static final String NO_CAR_VALUE = "no car";

    private static final String DELETE_CAR
            = "DELETE FROM cars  WHERE id IN"
            + "(SELECT user_info.car_id FROM user_info WHERE user_id = ? );";

    private static final String DELETE_USER_BY_ID
            = "DELETE FROM users WHERE id = ?";

    private static final String UPDATE_USER_PASSWORD
            = "UPDATE users SET password = ?, salt = ? WHERE id = ?";

    private static final String SELECT_USER_INFO_ID_BY_USERS_ID
            = "SELECT id FROM user_info WHERE user_id = ?";

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
    public User create(User entity) throws ExceptionDao {
        int userId = -1;
        ResultSet resultSet = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(INSERT_INTO_USERS, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUserInf
                     = connection.prepareStatement(INSERT_INTO_USER_INFO);
             PreparedStatement statementTakeCreatedUser
                     = connection.prepareStatement(SELECT_ID_LOGIN_ROLE_BY_ID)) {

            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getSalt());
            statement.executeUpdate();


            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }

            logger.debug(String.format("new user id: %d", userId));
//

            statementUserInf.setInt(1, userId);
            statementUserInf.setString(2, entity.getSurname());
            statementUserInf.setString(3, entity.getName());
            statementUserInf.setString(4, entity.getBirthday().toString());
            statementUserInf.setInt(5, Integer.parseInt(entity.getGender()));
            statementUserInf.setInt(6, Integer.parseInt(entity.getCountry()));
            statementUserInf.setString(7, entity.getPassportNumber());
            statementUserInf.setString(8, entity.getPassportDateOfIssue().toString());
            statementUserInf.setString(9, entity.getPhoneNumber());
            statementUserInf.setString(10, entity.getEmail());

            statementUserInf.executeUpdate();

            connection.commit();

            statementTakeCreatedUser.setInt(1, userId);
            resultSet = statementTakeCreatedUser.executeQuery();
            while (resultSet.next()) {
                logger.debug(String.format("new user take from db: %d", 2));

                entity = new User();
                entity.setLogin(resultSet.getString("login"));
                entity.setRole(resultSet.getInt("role"));
                entity.setId(resultSet.getInt("id"));
                logger.debug(String.format("new user created: role: %s, %s", entity.getRole(), entity));

            }


        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }

        }


        return entity;
    }

    /**
     * Remove user from database.
     *
     * @param id - identity of removing user.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(final Integer id) throws ExceptionDao {

        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        }
        return true;
    }


    /**
     * Update user values.
     *
     * @param entity - user which needed to be update
     *               with new params.
     * @return - user with new params.
     */
    @Override
    public User update(final User entity) throws ExceptionDao {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_INFO)) {
            setStatementDataToUpdateUser(statement, entity);
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        }
        return entity;
    }

    private void setStatementDataToUpdateUser(PreparedStatement statement, User user) throws SQLException {
        logger.debug(user);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getName());
        statement.setString(3, user.getSurname());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getBirthday().toString());
        statement.setString(6, user.getPhoneNumber());
        statement.setInt(7, Integer.parseInt(user.getCountry()));
        statement.setString(8, user.getPassportNumber());
        statement.setString(9, user.getPassportDateOfIssue().toString());
        statement.setInt(10, Integer.parseInt(user.getGender()));
        statement.setInt(11, user.getId());
        statement.setInt(12, user.getId());
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
    public User findEntityById(final Integer id) throws ExceptionDao {
        User user = null;
        ResultSet resultSet = null
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_FROM_TRIP_BY_ID);) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));

                String carModel = resultSet.getString("brand_and_model_id");

                if (carModel != null) {
                    Car car = new Car();
                    car.setModel(carModel);
                    car.setYearOfProduce(resultSet.getInt("year_of_produce"));
                    car.setAirConditioner(resultSet.getString("climate_type_id"));
                    user.setCar(car);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }

        }
        return user;
    }

    @Override
    public String getSalt(final String login) throws ExceptionDao {
        String salt = null;
        ResultSet resultSet = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_SALT_BY_LOGIN);) {
            statement.setString(1, login);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                salt = resultSet.getString("salt");

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }

        return salt;
    }

    @Override
    public User findUserByLoginAndPassword(final String login,
                                           final String password)
            throws ExceptionDao {
        User user = null;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PWD);) {
            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setRole(resultSet.getInt("role"));
                user.setId(resultSet.getInt("id"));

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }

        }
        return user;
    }

    @Override
    public User addCar(final User user) throws ExceptionDao {
        int carId = -1;
        Car car = user.getCar();
        ResultSet idNewCarSet = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(INSERT_INTO_CARS,
                Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUserInfUpdate
                     = connection.prepareStatement(UPDATE_CAR_INTO_USER_INFO);) {

            deleteCar(user);

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getYearOfProduce());
            statement.setString(3, car.getAirConditioner());
            statement.executeUpdate();


            idNewCarSet = statement.getGeneratedKeys();
            if (idNewCarSet.next()) {
                carId = idNewCarSet.getInt(1);
            }

            logger.debug(String.format("new car id: %d", carId));


            statementUserInfUpdate.setInt(1, carId);
            statementUserInfUpdate.setInt(2, user.getId());

            statementUserInfUpdate.executeUpdate();


        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        } finally {
            if (idNewCarSet != null) {
                try {
                    idNewCarSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }

        }


        return user;
    }

    @Override
    public User deleteCar(User user) throws ExceptionDao {
        try (PreparedStatement statementDeleteCarIfExist
                     = connection.prepareStatement(DELETE_CAR);) {


            logger.debug(String.format("user id (del car): %s", user.getId()));
            statementDeleteCarIfExist.setInt(1, user.getId());
            statementDeleteCarIfExist.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        }
        return user;
    }


    @Override
    public boolean hasUserCar(int id) throws ExceptionDao {
        boolean hasCar = false;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CHECK_HAS_USER_CAR)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String carExist = resultSet.getString("car");
                hasCar = !carExist.equals(NO_CAR_VALUE);
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return hasCar;
    }

    public User findUserInfoWithCar(int id) throws ExceptionDao {
        User userInfo = null;
        ResultSet resultSet = null;
        try (PreparedStatement userInfoStatement = connection.prepareStatement(SELECT_ALL_USER_INFO_WITH_CAR_BY_ID);) {
            userInfoStatement.setInt(1, id);
            resultSet = userInfoStatement.executeQuery();

            while (resultSet.next()) {
                logger.debug(String.format("new user take from db: %d", 2));

                userInfo = new User();
                userInfo.setLogin(resultSet.getString("login"));
                userInfo.setSurname(resultSet.getString("surname"));
                userInfo.setName(resultSet.getString("name"));
                userInfo.setBirthday(resultSet.getString("birthday"));
                userInfo.setGender(resultSet.getString("gender_id"));
                userInfo.setCountry(resultSet.getString("country_id"));
                userInfo.setPassportNumber(resultSet.getString("passport_number"));
                userInfo.setPassportDateOfIssue(resultSet.getString("passport_date_of_issue"));
                userInfo.setPhoneNumber(resultSet.getString("phone"));
                userInfo.setEmail(resultSet.getString("email"));
                logger.debug("user data ok creating car");
                Car userCar = new Car();
                logger.debug("get brand OK");
                userCar.setModel(resultSet.getString("brand_and_model_id"));
                userCar.setYearOfProduce(Integer.parseInt(resultSet.getString("year_of_produce").split("-")[0]));
                userCar.setAirConditioner(resultSet.getString("climate_type_id"));

                userInfo.setCar(userCar);
                logger.debug(String.format("new user created: role: %s", userInfo));

            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return userInfo;
    }

    @Override
    public User findUserInfoWithoutCar(int id) throws ExceptionDao {
        User userInfo = null;

        logger.debug(String.format("new user take from db: %d", 2));
        ResultSet resultSet = null;
        try (PreparedStatement userInfoStatement = connection.prepareStatement(SELECT_ALL_USER_INFO_WITHOUT_CAR_BY_ID);) {
            userInfoStatement.setInt(1, id);
            resultSet = userInfoStatement.executeQuery();
            while (resultSet.next()) {
                userInfo = new User();
                userInfo.setLogin(resultSet.getString("login"));
                userInfo.setSurname(resultSet.getString("surname"));
                userInfo.setName(resultSet.getString("name"));
                userInfo.setBirthday(resultSet.getString("birthday"));
                userInfo.setGender(resultSet.getString("gender_id"));
                userInfo.setCountry(resultSet.getString("country_id"));
                userInfo.setPassportNumber(resultSet.getString("passport_number"));
                userInfo.setPassportDateOfIssue(resultSet.getString("passport_date_of_issue"));
                userInfo.setPhoneNumber(resultSet.getString("phone"));
                userInfo.setEmail(resultSet.getString("email"));
                logger.debug("user data ok");

                logger.debug(String.format("new user created: role: %s", userInfo));

            }
            return userInfo;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public boolean updateUserPassword(User user) throws ExceptionDao {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getSalt());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        }
        return true;
    }

    @Override
    public int findUserInfoIdByUsersId(int usersId) throws ExceptionDao {
        int userInfoId = 0;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_INFO_ID_BY_USERS_ID)) {
            statement.setInt(1, usersId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userInfoId = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }

        return userInfoId;
    }
}
