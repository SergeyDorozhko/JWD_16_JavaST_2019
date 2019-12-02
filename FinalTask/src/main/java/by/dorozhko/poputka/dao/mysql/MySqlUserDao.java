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
    //TODO optimize query
    private static final String SELECT_ALL_USERS
            = "SELECT users.id, users.login from users";

    private static final String SELECT_USER_NAME_BY_ID
            = " SELECT user_info.name FROM user_info"
            + " WHERE id = ?;";

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
            + "country, passport_number, passport_date_of_issue, phone, email)"
            + " values (?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_INTO_CARS
            = "INSERT INTO cars (brand_and_model_id, year_of_produce,"
            + "climate_type_id) values (?,?,?)";
    private static final String UPDATE_CAR_INTO_USER_INFO
            = "UPDATE user_info SET car_id = ? WHERE user_id = ?;";
    private static final String SELECT_ALL_USER_INFO_BY_ID
            = " SELECT login, surname, name, birthday, gender_id, country_name,"
            + " passport_number, passport_date_of_issue, phone, email, brand, model, year_of_produce, climate_type"
            + " from cars, users, user_info, car_climate, countries, car_models, car_brands"
            + " WHERE user_info.user_id = ? "
            + " AND user_info.user_id = users.id"
            + " AND user_info.country_id = countries.id"
            + " AND user_info.car_id =cars.id"
            + " AND cars.brand_and_model_id = car_models.id"
            + " AND car_models.brand_id = car_brands.id"
            + " AND cars.climate_type_id = car_climate.id;";

    /**
     * Method take connection to database and set it to realisation of Dao.
     *
     * @param newConnection - connection to database.
     */
    @Override
    public void setConnection(final Connection newConnection) {
        connection = newConnection;
    }

//    @Override
//    public Map<Integer, String> getGenderList() throws ExceptionDao {
//        Map<Integer, String> map = new HashMap<>();
//
//        try (ResultSet resultSet = connection.createStatement()
//                .executeQuery(SELECT_ALL_GENDERS);) {
//            while (resultSet.next()) {
//
//                Integer key = Integer.parseInt(resultSet.getString("id"));
//                String value = resultSet.getString("gender");
//                map.put(key, value);
//            }
//
//        } catch (SQLException e) {
//            logger.error(e);
//            throw new ExceptionDao(e);
//        }
//
//        return map;
//    }

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


//  TODO  Best method creating user.


            ResultSet idNewUserSet = statement.getGeneratedKeys();
            if (idNewUserSet.next()) {
                userId = idNewUserSet.getInt(1);
            }

            logger.debug(String.format("new user id: %d", userId));
//

            statementUserInf.setInt(1, userId);
            statementUserInf.setString(2, entity.getSurname());
            statementUserInf.setString(3, entity.getName());
            statementUserInf.setString(4, entity.getBirthday().toString());
            statementUserInf.setInt(5, Integer.parseInt(entity.getGender()));
            statementUserInf.setString(6, entity.getCountry());
            statementUserInf.setString(7, entity.getPassportNumber());
            statementUserInf.setString(8, entity.getPassportDateOfIssue().toString());
            statementUserInf.setString(9, entity.getPhoneNumber());
            statementUserInf.setString(10, entity.getEmail());

            statementUserInf.executeUpdate();

            connection.commit();

            statementTakeCreatedUser.setInt(1, userId);
            ResultSet resultSet = statementTakeCreatedUser.executeQuery();
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
    public User findEntityById(final Integer id) throws ExceptionDao {
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_NAME_BY_ID);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }
        return user;
    }

    @Override
    public String getSalt(final String login) throws ExceptionDao {
        String salt = null;

        try (PreparedStatement statement
                     = connection.prepareStatement(SELECT_SALT_BY_LOGIN);) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                salt = resultSet.getString("salt");

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return salt;
    }

    @Override
    public User findUserByLoginAndPassword(final String login,
                                           final String password)
            throws ExceptionDao {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PWD);) {
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setRole(resultSet.getInt("role"));
                user.setId(resultSet.getInt("id"));

            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }
        return user;
    }

    @Override
    public User addCar(final User user) throws ExceptionDao {
        int carId = -1;
        Car car = user.getCar();
        User userInfo = null;
        try (PreparedStatement statement
                     = connection.prepareStatement(INSERT_INTO_CARS, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUserInfUpdate
                     = connection.prepareStatement(UPDATE_CAR_INTO_USER_INFO);
             PreparedStatement statementTakeUser
                     = connection.prepareStatement(SELECT_ALL_USER_INFO_BY_ID)) {

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getYearOfProduce());
            statement.setString(3, car.getAirConditioner());
            statement.executeUpdate();


            ResultSet idNewCarSet = statement.getGeneratedKeys();
            if (idNewCarSet.next()) {
                carId = idNewCarSet.getInt(1);
            }

            logger.debug(String.format("new car id: %d", carId));


            statementUserInfUpdate.setInt(1, carId);
            statementUserInfUpdate.setInt(2, user.getId());

            statementUserInfUpdate.executeUpdate();

            connection.commit();

            statementTakeUser.setInt(1, user.getId());
            ResultSet resultSet = statementTakeUser.executeQuery();
            while (resultSet.next()) {
                logger.debug(String.format("new user take from db: %d", 2));

                userInfo = new User();
                userInfo.setLogin(resultSet.getString("login"));
                userInfo.setSurname(resultSet.getString("surname"));
                userInfo.setName(resultSet.getString("name"));
                userInfo.setBirthday(resultSet.getString("birthday"));
                userInfo.setGender(resultSet.getString("gender_id"));
                userInfo.setCountry(resultSet.getString("country_name"));
                userInfo.setPassportNumber(resultSet.getString("passport_number"));
                userInfo.setPassportDateOfIssue(resultSet.getString("passport_date_of_issue"));
                userInfo.setPhoneNumber(resultSet.getString("phone"));
                userInfo.setEmail(resultSet.getString("email"));
                logger.debug("user data ok creating car");
                Car userCar = new Car();
                userCar.setBrand(resultSet.getString("brand"));
                logger.debug("get brand OK");
                userCar.setModel(resultSet.getString("model"));
                userCar.setYearOfProduce(Integer.parseInt(resultSet.getString("year_of_produce").split("-")[0]));
                userCar.setAirConditioner(resultSet.getString("climate_type"));

                userInfo.setCar(userCar);
                logger.debug(String.format("new user created: role: %s", userInfo));

            }


        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }


        return userInfo;
    }

}
