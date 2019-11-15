package by.dorozhko.poputka.dao.user.impl;


import by.dorozhko.poputka.dao.QuerySpecification;
import by.dorozhko.poputka.dao.UserDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;
import by.dorozhko.poputka.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlUserDao implements UserDAO {

    private Connection connect;

    private static final String INSERT_INTO_USERS = "INSERT INTO users (login, password, role) values (?,?,?)";
    private static final String SELECT_ID_NEW_USER = "SELECT id FROM users WHERE login=?";
    private static final String INSERT_INTO_USER_INFO = "INSERT INTO user_info(user_id, surname, name, birthday, gender, country, passport_number, passport_date_of_issue, phone, email, car_id, driving_experience_since) values (?,?,?,?,?,?,?,?,?,?,?,?)";


    @Override
    public boolean setConnection(Connection connection) {
        connect = connection;
        return false;
    }

    @Override
    public boolean create(Entity entity) {

        boolean result = false;
        User user = (User) entity;
        try {
            connect.setAutoCommit(false);
            PreparedStatement statement = connect.prepareStatement(INSERT_INTO_USERS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole());
            statement.executeUpdate();
            PreparedStatement statementRequest = connect.prepareStatement(SELECT_ID_NEW_USER);
            statementRequest.setString(1, user.getLogin());
            ResultSet resultSet = statementRequest.executeQuery();
            int userId = 0;
            if (resultSet.next()){
                userId = resultSet.getInt("id");


            }


            PreparedStatement statementUserInf = connect.prepareStatement(INSERT_INTO_USER_INFO);
            statementUserInf.setInt(1, userId);
            statementUserInf.setString(2, user.getSurname());
            statementUserInf.setString(3, user.getName());
            statementUserInf.setString(4, user.getBirthday());
            statementUserInf.setByte(5, user.getGender());
            statementUserInf.setString(6, user.getCountry());
            statementUserInf.setString(7, user.getPassportNumber());
            statementUserInf.setString(8, user.getPassportDateOfIssue());
            statementUserInf.setString(9, user.getPhoneNumber());
            statementUserInf.setString(10, user.getEmail());
            //TODO car
            statementUserInf.setString(11, null);
            statementUserInf.setString(12, null);

            statementUserInf.executeUpdate();
           connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public boolean update(Entity entity) {
        return false;
    }

    @Override
    public List<Entity> query(QuerySpecification  specification, String actionData) throws ExceptionDao {
        return specification.getAllEntityByQuery(connect, actionData);
    }
}
