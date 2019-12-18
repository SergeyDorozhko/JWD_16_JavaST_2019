package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.TransactionFactory;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.Role;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.testng.Assert;
import org.testng.annotations.*;


import java.sql.*;
import java.util.*;

import static org.testng.Assert.assertThrows;

public class UserServiceImplTest {

    private Connection connection = null;

    private List<User> usersList = new ArrayList<>();

    private UserService service;

    @BeforeClass
    public void setConnection() {
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

        service = ServiceFactory.getInstance().getUserService();

    }

    @AfterClass
    public void closeConnection() {
        try (PreparedStatement statementUserInfo
                     = connection.prepareStatement(
                             "DELETE FROM user_info WHERE user_id = ?;");
             PreparedStatement statementUsers
                     = connection.prepareStatement(
                             "DELETE FROM users WHERE id = ?;")) {

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

    @AfterSuite
    public void closeConnectionPool() {
        TransactionFactory.getInstance().closeConnectionPool();
    }


    @Test(description = "Test add user", dataProvider = "users for add test")
    public void addUserTest(User user, boolean expected) {
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
    private Object[][] createUsersForTest() {
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


    @Test(description = "test find user info by id",
            dataProvider = "data for find user by id test")
    public void findUserByIdTest(int id, boolean expectedResult) {

        User actualUser = null;
        try {
            actualUser = service.findById(id);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }
        List<User> dublicateDbUsers = createUsersEqualsDB();
        Assert.assertEquals(dublicateDbUsers.contains(actualUser), expectedResult);

    }

    private List<User> createUsersEqualsDB() {
        List<User> list = new ArrayList<>();
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

        list.add(diff);


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

        list.add(gav);
        return list;
    }


    @DataProvider(name = "data for find user by id test")
    private Object[][] createDataForFindBuIdTest() {
        return new Object[][]{
                {55, true},
                {56, true},
                {57, false},
                {-55, false},
                {0, false},
                {1, false}


        };
    }

    @Test(description = "find user by id exception test",
            dataProvider = "data for find user by id exception test")
    public void findUserByIdExceptionTest(int id) {
        assertThrows(ExceptionService.class, () -> service.findById(id));
    }

    @DataProvider(name = "data for find user by id exception test")
    private Object[] createDataForFindBuIdExceptonTest() {
        return new Object[]{
                -55,
                0,
        };
    }


    @Test(description = "positive test sing in",
            dataProvider = "data for testing positive scenario of sing in")
    public void singInTest(final String[] loginPassword,
                           final User expextedUserWithRole)
            throws ExceptionService {
        User actualUser = service.singIn(loginPassword[0], loginPassword[1]);
        if (actualUser != null) {
            String actualRole = actualUser.getRole().toString();
            Assert.assertEquals(actualRole, expextedUserWithRole.getRole().toString());
        } else {
            Assert.assertEquals(actualUser, expextedUserWithRole);
        }
    }

    @DataProvider(name = "data for testing positive scenario of sing in")
    private Object[][] createDataForPositiveSingIn() {
        return new Object[][]{
                {new String[]{"diff", "diff"}, createUserWithRole(Role.USER)},
                {new String[]{"gav", "gav"}, createUserWithRole(Role.USER)},
                {new String[]{"admin", "admin"}, createUserWithRole(Role.ADMINISTRATOR)},
                {new String[]{"admin", "gav"}, null},
                {new String[]{"gav", "Gav"}, null},
                {new String[]{"gav", "gAv"}, null},
                {new String[]{"gav", "gаv"}, null} // "a" cyrillic  alphabet.


        };
    }

    private User createUserWithRole(Role role) {
        User user = new User();
        user.setRole(role.getId());
        return user;
    }

    @Test(description = "negotive test sing in",
            dataProvider = "data for testing negotive scenario of sing in")
    public void singInErrorTest(final String login,
                                final String password)
            throws ExceptionService {
        assertThrows(ExceptionService.class,
                () -> service.singIn(login, password));
    }

    @DataProvider(name = "data for testing negotive scenario of sing in")
    private Object[][] createDataForNegotiveSingIn() {
        return new Object[][]{
                {"diff", "d/"},
                {"diff", "d*"},
                {"diff", "d\\"},
                {"diff", "d>"},
                {"diff", "d<"},
                {"diff(", "diff"},
                {"diff*", "diff"},
                {"diff/", "diff"},
                {"dif\\f", "diff"},
                {"diff<", "diff"},
                {"di>ff", "diff"},
                {"%diff", "diff"},
                {"d#iff", "diff"},
                {"уше", "diff"},

        };
    }

    @Test(dependsOnMethods = {"addUserTest"},
            description = "find all users test")
    public void findAllTest() {
        List<User> actulList = service.findAll();
        int expectedSize = usersList.size() + 3; //number "3" because data base has admin, gav, diff before test
        Assert.assertEquals(actulList.size(), expectedSize);
    }


    @Test(description = "test change password",
            dataProvider = "data for update Password")
    public void updateUserPasswordTest(Object[] userAndNewPssword,
                                       final boolean expectedResult)
            throws ExceptionService {
        User actualUser = (User) userAndNewPssword[0];
        String newPassword = (String) userAndNewPssword[1];
        boolean actualResult = service.updateUserPassword(actualUser, newPassword);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "data for update Password")
    private Object[][] updatePasswordData() {
        return new Object[][] {
                {new Object[]{ userForUpdatePassword("diff"), "newPassword"}, true}, //BE SURE!!!! change password back
                {new Object[]{ userForUpdatePassword("diff"), "OneMoreTime"}, false},
                {new Object[]{ userForUpdatePassword("OneMoreTime"), "diff"}, false},
                {new Object[]{ userForUpdatePassword("newPassword"), "diff"}, true}, //BE SURE!!!! change password back
                {new Object[]{ userForUpdatePassword("dif"), "diff"}, false},
                {new Object[]{ userForUpdatePassword("diFF"), "diff"}, false},

        };
    }

    private User userForUpdatePassword(String oldPassword) {
        User user = new User();
        user.setId(55);
        user.setLogin("diff");
        user.setPassword(oldPassword);
        return user;
    }

}
