package by.dorozhko.poputka;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.dao.mysql.MySqlJourneyDAO;
import by.dorozhko.poputka.dao.mysql.MySqlUserDao;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.Entity;
import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.Service;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.impl.JourneyServiceImpl;
import by.dorozhko.poputka.services.impl.UserServiceImpl;
import by.dorozhko.poputka.services.security.HashingPBKDF2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ExceptionDao {

        testHashPwd();
    }

    public static void testAddUserService() {
        User user = new User();
        user.setLogin("Mavr");
        user.setPassword("Mavr");
        user.setRole(1);
        user.setName("Ivan");
        user.setSurname("Taranov");
        user.setBirthday("1985-10-17");
        user.setCountry("Belarus");
        byte gendor = 1;
        user.setGender(gendor);
        user.setPassportNumber("MP2454622");
        user.setPassportDateOfIssue("2015-03-28");
        user.setPhoneNumber("+375295608860");
        user.setEmail("mavr@tut.by");

        UserService service = ServiceFactory.getInstance().getUserService();
        System.out.println("is user added - " + service.add(user));
    }

    public static void testFindAllUserService() {
        UserService service = new UserServiceImpl();
        List<User> list = service.findAll();
        for (User user : list) {
            System.out.println("user id = " + user.getId() + ", user login = " + user.getLogin());
        }
    }

    public static void testFindAllJourneyService() throws ExceptionDao {
        JourneyService service = new JourneyServiceImpl();
        System.out.println(service.findAllJourneyShort());

    }

    public static void testInClass() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/poputka_db?useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=Europe/Minsk", "root", "Dorogich1");

            Statement st = connect.createStatement();


            ResultSet rs = st.executeQuery("SELECT id, brand, model, year_of_produce, air_conditioner FROM cars");

            List<Car> list = new ArrayList<>();
            Car car;
            while (rs.next()) {
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYearOfProduce(rs.getInt("year_of_produce"));
                list.add(car);
            }

            for (Car see : list) {


                System.out.println(see);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void testDB() {
        FactoryDao factoryDao = FactoryDao.getInstance();

//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.takeConnection();


        UserDAO userDAO = factoryDao.getUserDAO();
        //     userDAO.setConnection(connection);


        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(userDAO);
        List<User> result = null;
        try {
            result = userDAO.findAll();
        } catch (ExceptionDao exceptionDao) {
            exceptionDao.printStackTrace();
        }

        for (Entity user : result) {
            System.out.println(user);
        }


        User user = new User();
        user.setLogin("gav");
        user.setPassword("gav");
        user.setRole(1);
        user.setName("Uriy");
        user.setSurname("Gavrilov");
        user.setBirthday("1963-08-17");
        user.setCountry("Belarus");
        byte gendor = 1;
        user.setGender(gendor);
        user.setPassportNumber("MP24645682");
        user.setPassportDateOfIssue("2013-02-03");
        user.setPhoneNumber("+375298005060");
        user.setEmail("gav@tut.by");

        try {
            System.out.println(userDAO.create(user));
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            exceptionDao.printStackTrace();
        }

        transaction.end();

//        pool.releaseConnection(connection);
//        pool.closePool();

    }



    public static void testLogin(){
        FactoryDao factoryDao = FactoryDao.getInstance();



        UserDAO userDAO = factoryDao.getUserDAO();



        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(userDAO);

        try {
            User user = userDAO.findUserByLoginAndPassword("admin", "admin");
            System.out.println(user.getLogin() + user.getRole());
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            exceptionDao.printStackTrace();
        }
        transaction.end();

    }


    public static void testHashPwd() {
        HashingPBKDF2 hashingPBKDF2 = HashingPBKDF2.getInstance();
        //generate salt
        hashingPBKDF2.generateSalt();

        //generate pwd in hash in hex string value.
        String pwd = hashingPBKDF2.generatePwdHash("gav");

        //make byte array hash of pwd (from string in hash).
        byte[] val = new byte[pwd.length() / 2];
        for (int i = 0; i < val.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(pwd.substring(index, index + 2), 16);
            val[i] = (byte) j;
        }


        System.out.println(Arrays.toString(val));
    }
}
