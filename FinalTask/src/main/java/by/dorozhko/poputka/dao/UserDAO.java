package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.User;

public interface UserDAO extends InterfaceDAO<Integer, User> {

    User findUserByLoginAndPassword(String login, String password) throws ExceptionDao;

}
