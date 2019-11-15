package by.dorozhko.poputka.dao.connectionImpl;

import by.dorozhko.poputka.dao.ConnectionDAO;
import by.dorozhko.poputka.dao.QuerySpecification;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionDaoImpl implements ConnectionDAO {
    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private Connection connect;


    //TODO rootServlet catalog if need
    public Connection setupConnection(final String rootCatalog) throws ExceptionDao {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("dbresource.database");
            String jdbcUrl = resourceBundle.getString("db.url");
            String userLogin = resourceBundle.getString("db.login");
            String pwd = resourceBundle.getString("db.password");
            connect = DriverManager.getConnection(jdbcUrl, userLogin, pwd);
        } catch (SQLException e) {
            logger.error("Connection error", e);
            throw new ExceptionDao(e);
        }
        return connect;
    }

    @Override
    public boolean setConnection(Connection connection) {
        return false;
    }

    @Override
    public boolean create(Entity entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public boolean update(Entity entity) {
        return false;
    }

    @Override
    public List<Entity> query(QuerySpecification specification, String actionQuery) {
        return null;
    }
}
