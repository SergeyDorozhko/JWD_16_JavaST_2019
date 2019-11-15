package by.dorozhko.poputka.dao.user.specificationImpl;

import by.dorozhko.poputka.dao.QuerySpecification;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;
import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShowAllUser implements QuerySpecification {
    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());
//TODO optimize query
    private static final String SELECT_ALL_USERS_INFO = "SELECT user_info.id, users.login, user_info.surname, user_info.name, user_info.birthday, user_info.gender, user_info.country, user_info.passport_number, user_info.passport_date_of_issue, user_info.phone, user_info.email, user_info.car_id, user_info.driving_experience_since\n" +
            "from user_info\n" +
            "inner join users ON user_info.user_id = users.id";

    @Override
    public List<Entity> getAllEntityByQuery(Connection connection, String actionData) throws ExceptionDao{
        List<Entity> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS_INFO);



            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                list.add(user);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return list;
    }
}
