package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;

import java.sql.Connection;
import java.util.List;

public interface InterfaceDAO <Key, Type extends Entity> {


    boolean setConnection(Connection connection);

    boolean create(Type entity) throws ExceptionDao;

    boolean delete(Key id);

    boolean delete(Type entity);

    Type update(Type entity);

    List<Type> findAll() throws ExceptionDao;

    Type findEntityById(Key id);
}
