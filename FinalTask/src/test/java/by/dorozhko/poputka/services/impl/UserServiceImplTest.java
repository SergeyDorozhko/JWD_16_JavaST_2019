package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.TransactionFactory;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.testng.Assert;
import org.testng.annotations.*;


import java.sql.*;
import java.util.*;

public class UserServiceImplTest {

    private Connection connection = null;

    private List<User> usersList = new ArrayList<>();

    private List<User> findUserList = new ArrayList<>();

    @BeforeTest
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


    @Test(description = "Test add user", dataProvider = "users for add test")
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


    @DataProvider(name = "users for add test")
    Object[][] createUsersForTest() {
        return new Object[][]{
                {new User("one", "one", "one", "one", "1", "2000-12-12", "1", "dsadasdasd", "2019-12-01", "375293334455", "dsada@ds.re"), true},
                {new User("two", "two", "two", "two", "1", "1930-12-10", "1", "MP2333339", "2017-12-02", "375293334400", "dsad@ds.re"), true},
                {new User("two", "two", "two", "two", "1", "1930-12-10", "1", "MP2333339", "2017-12-02", "375293334400", "dsad@ds.re"), false},
                {new User("th*", "th", "th", "two", "1", "1930-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th/", "th", "two", "1", "1930-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th\\", "two", "1", "1930-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two<", "1", "1930-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "-2", "1930-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "100", "1930-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "1530-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2100-12-10", "1", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "0", "MP23333pk", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p>", "2017-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2100-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "1100-12-02", "375293334422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2019-12-02", "37529333d422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2019-12-02", "37529333<422", "dsadds@ds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2019-12-02", "375293334422", "dsaddsds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2019-12-02", "375", "dsadd@sds.re"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2019-12-02", "375297300600", "dsadd@sds"), false},
                {new User("th", "th", "th", "two", "1", "2000-12-10", "2", "MP23333p", "2019-12-02", "", "dsadd@sds"), false},

        };
    }

    @BeforeClass
    private void createUsersEqualsDB() {
        User diff = new User();
        diff.setLogin("diff");
        diff.setSurname("diff");
        diff.setName("diff");
        diff.setBirthday("1970-01-14");
        diff.setGender("Famale");
        diff.setCountry("Россия");
        diff.setPassportNumber("MP2333332");
        diff.setPassportDateOfIssue("2019-12-10");
        diff.setPhoneNumber("375297300600");
        diff.setEmail("diff@mail.ru");

        Car diffCar = new Car();
        diffCar.setBrand("ACURA");
        diffCar.setModel("LEGEND");
        diffCar.setYearOfProduce(2000);
        diffCar.setAirConditioner("climate control");
        diff.setCar(diffCar);

        findUserList.add(diff);


        User gav = new User();
        gav.setLogin("gav");
        gav.setSurname("gav");
        gav.setName("gav");
        gav.setBirthday("2000-12-14");
        gav.setGender("Famale");
        gav.setCountry("Беларусь");
        gav.setPassportNumber("MC2345678");
        gav.setPassportDateOfIssue("2019-12-04");
        gav.setPhoneNumber("375297576719");
        gav.setEmail("gav@mail.ru");

        findUserList.add(gav);
    }

    @Test(description = "test find user info by id", dataProvider = "data for find user by id test")
    public void findUserByIdTest(int id, boolean expectedResult) {

        UserService service = ServiceFactory.getInstance().getUserService();
        User actualUser = null;
        try {
            actualUser = service.findById(id);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }

        Assert.assertEquals(findUserList.contains(actualUser), expectedResult);

    }

    @DataProvider(name = "data for find user by id test")
    Object[][] createDataForFindBuIdTest() {
        return new Object[][]{
                {55, true},
                {56, true},
                {57, false},
                {-55, false},
                {0, false},
                {1, false}


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
