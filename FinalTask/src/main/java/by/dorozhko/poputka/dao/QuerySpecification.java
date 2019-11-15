package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;

import java.sql.Connection;
import java.util.List;

public interface QuerySpecification {

    List<Entity> getAllEntityByQuery(Connection connection, String actionData) throws ExceptionDao;
}
