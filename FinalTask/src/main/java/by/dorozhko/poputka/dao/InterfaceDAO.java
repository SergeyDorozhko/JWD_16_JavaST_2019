package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;

import java.sql.Connection;
import java.util.List;

public interface InterfaceDAO {

    default Connection setupConnection(String path) throws ExceptionDao {
     throw new UnsupportedOperationException();
    }

    boolean setConnection(Connection connection);

    boolean create(Entity entity);

    boolean delete(int id);

    boolean delete(Entity entity);

    boolean update(Entity entity);

    List<Entity> query(QuerySpecification specification, String actionData) throws ExceptionDao;
}
