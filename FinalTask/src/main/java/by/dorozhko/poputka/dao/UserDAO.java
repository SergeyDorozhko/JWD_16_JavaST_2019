package by.dorozhko.poputka.dao;

        import by.dorozhko.poputka.dao.exception.ExceptionDao;
        import by.dorozhko.poputka.entity.User;


public interface UserDAO extends InterfaceDAO<Integer, User> {

    String getSalt(String login) throws ExceptionDao;
    User findUserByLoginAndPassword(String login, String password) throws ExceptionDao;
    User addCar(User user) throws ExceptionDao;
}
