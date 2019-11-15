package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;

import java.sql.Connection;

public interface ConnectionDAO extends InterfaceDAO {
    Connection setupConnection(String path) throws ExceptionDao;

}
