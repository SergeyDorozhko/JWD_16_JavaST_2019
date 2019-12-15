package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.TransactionFactory;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class UserServiceImplTest {

    private Connection connection = null;

    private List<User> usersList = new ArrayList<>();

    @BeforeClass
    private void setConnection() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dbresource.database");
        String driver = resourceBundle.getString("db.driver");
        String jdbcUrl = resourceBundle.getString("db.url");
        String userLogin = resourceBundle.getString("db.login");
        String pwd = resourceBundle.getString("db.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(jdbcUrl, userLogin, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TransactionFactory.getInstance().initConnectionPool();
    }

    @Test(description = "Test add user positive scenario", dataProvider = "users for add positive test")
    private void addUserTest(User user, boolean expected) {
        UserService service = ServiceFactory.getInstance().getUserService();
        User createdUser = null;
        try {
            createdUser = service.add(user);
            usersList.add(createdUser);
            
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }
        Assert.assertEquals(createdUser != null, expected);
    }

    @DataProvider(name = "users for add positive test")
    Object[][] createUsersForTest() {
        return new Object[][]{
                {new User("one", "one", "one", "one", "1", "2000-12-12", "1", "dsadasdasd", "2019-12-01", "375293334455", "dsada@ds.re"), true},
                {new User("two", "two", "two", "two", "1", "1930-12-10", "1", "MP2333339", "2017-12-02", "375293334400", "dsad@ds.re"), true},
                {new User("two", "two", "two", "two", "1", "1930-12-10", "1", "MP2333339", "2017-12-02", "375293334400", "dsad@ds.re"), false},

        };
    }

    @AfterTest
    private void closeConnection() {
        TransactionFactory.getInstance().closeConnectionPool();


        try (PreparedStatement statementUserInfo = connection.prepareStatement("DELETE FROM user_info WHERE user_id = ?;");
             PreparedStatement statementUsers = connection.prepareStatement("DELETE FROM users WHERE id = ?;")) {

            for (User user : usersList) {
                statementUserInfo.setInt(1, user.getId());
                statementUserInfo.executeUpdate();
                statementUsers.setInt(1, user.getId());
                statementUsers.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {

            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
