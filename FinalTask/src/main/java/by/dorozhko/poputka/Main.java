package by.dorozhko.poputka;

import by.dorozhko.poputka.dao.FactoryDao;
import by.dorozhko.poputka.dao.InterfaceDAO;
import by.dorozhko.poputka.dao.SpecificationProvider;
import by.dorozhko.poputka.dao.connectionImpl.ConnectionPool;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.securityMOVEtoSERVICE.HashingPBKDF2;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.Entity;
import by.dorozhko.poputka.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ExceptionDao {

        testHashPwd();

    }


    public static void testInClass(){
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/poputka_db?useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=Europe/Minsk", "root", "Dorogich1");

            Statement st = connect.createStatement();


            ResultSet rs = st.executeQuery("SELECT id, brand, model, year_of_produce, air_conditioner FROM cars");

            List<Car> list = new ArrayList<>();
            Car car;
            while (rs.next()){
                car = new Car();
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYearOfProduce(rs.getInt("year_of_produce"));
                list.add(car);
            }

            for(Car see: list) {


                System.out.println(see);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void testDB() throws ExceptionDao{
        FactoryDao factoryDao = FactoryDao.getInstance();

//        InterfaceDAO connect = factoryDao.getConnectionDAO();
//        Connection connection = connect.setupConnection("no need");

        ConnectionPool pool = ConnectionPool.getInstance();

        Connection connection = pool.takeConnection();

        InterfaceDAO userDAO  = factoryDao.getUserDAO();
        userDAO.setConnection(connection);
        SpecificationProvider provider = SpecificationProvider.getInstance();
        List<Entity> result = userDAO.query(provider.getSpecification("showAllUsers"), "no add info");

        for(Entity user: result) {
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

        System.out.println(userDAO.create(user));

        pool.releaseConnection(connection);
        pool.closePool();

    }


    public static void testHashPwd(){
        HashingPBKDF2 hashingPBKDF2 = HashingPBKDF2.getInstance();
        //generate salt
        hashingPBKDF2.generateSalt();

        //generate pwd in hash in hex string value.
        String pwd = hashingPBKDF2.generatePwdHash("Admin");

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
