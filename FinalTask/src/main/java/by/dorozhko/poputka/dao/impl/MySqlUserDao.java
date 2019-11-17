package by.dorozhko.poputka.dao.impl;


import by.dorozhko.poputka.dao.UserDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDAO {

    private Connection connection;

    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());

    //TODO optimize query
    private static final String SELECT_ALL_USERS = "SELECT users.login from users";

    //TODO optimize query
    private static final String SELECT_ALL_USER_INFO = "SELECT user_info.id, users.login, user_info.surname, user_info.name, user_info.birthday, user_info.gender, user_info.country, user_info.passport_number, user_info.passport_date_of_issue, user_info.phone, user_info.email, user_info.car_id, user_info.driving_experience_since\n" +
            "from user_info\n" +
            "inner join users ON user_info.user_id = users.id";

    private static final String INSERT_INTO_USERS = "INSERT INTO users (login, password, role) values (?,?,?)";
    private static final String SELECT_ID_NEW_USER = "SELECT id FROM users WHERE login=?";
    private static final String INSERT_INTO_USER_INFO = "INSERT INTO user_info(user_id, surname, name, birthday, gender, country, passport_number, passport_date_of_issue, phone, email, car_id, driving_experience_since) values (?,?,?,?,?,?,?,?,?,?,?,?)";


    @Override
    public boolean setConnection(Connection connection) {
        this.connection = connection;
        return false;
    }

    @Override
    public boolean create(User entity) throws ExceptionDao {

        boolean result = false;
        try {
       //     connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_USERS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole());
            statement.executeUpdate();
            PreparedStatement statementRequest = connection.prepareStatement(SELECT_ID_NEW_USER);
            statementRequest.setString(1, entity.getLogin());
            ResultSet resultSet = statementRequest.executeQuery();
            int userId = 0;
            if (resultSet.next()){
                userId = resultSet.getInt("id");


            }


            PreparedStatement statementUserInf = connection.prepareStatement(INSERT_INTO_USER_INFO);
            statementUserInf.setInt(1, userId);
            statementUserInf.setString(2, entity.getSurname());
            statementUserInf.setString(3, entity.getName());
            statementUserInf.setString(4, entity.getBirthday());
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
  //         connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDao(e);
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }

        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public List<User> findAll() throws ExceptionDao {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);



            while (resultSet.next()){
                User user = new User();

                user.setLogin(resultSet.getString("login"));

                list.add(user);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return list;
    }


    @Override
    public User findEntityById(Integer id) {
        //TODO realisation
        return null;
    }
}
