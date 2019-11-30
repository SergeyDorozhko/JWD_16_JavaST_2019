package by.dorozhko.poputka.dao.mysql;

import by.dorozhko.poputka.dao.CatalogDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlCatalogDAO implements CatalogDAO {
    private Connection connection;
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SELECT_ALL_GENDERS
            = "SELECT id, gender FROM gender;";

    private static final String SELECT_ALL_COUNTRIES
            = "SELECT id, country_name FROM countries;";

    @Override
    public Map<Integer, String> getCountryList() throws ExceptionDao {
        Map<Integer, String> map = new HashMap<>();

        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL_COUNTRIES);) {
            while (resultSet.next()) {

                Integer key = Integer.parseInt(resultSet.getString("id"));
                String value = resultSet.getString("country_name");
                map.put(key, value);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return map;
    }

    @Override
    public Map<Integer, String> getGenderList() throws ExceptionDao {
        Map<Integer, String> map = new HashMap<>();

        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(SELECT_ALL_GENDERS);) {
            while (resultSet.next()) {

                Integer key = Integer.parseInt(resultSet.getString("id"));
                String value = resultSet.getString("gender");
                map.put(key, value);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return map;
    }

    /**
     * Method take connection to database and set it to realisation of Dao.
     *
     * @param newConnection - connection to database.
     */
    @Override
    public void setConnection(Connection newConnection) {
        connection = newConnection;
    }

    /**
     * Save new entity to database.
     *
     * @param entity -entity which will saved to database.
     * @return - result of saving. return true value if successfully.
     * @throws ExceptionDao - generating if some values conflicts
     *                      with existed in database.
     */
    @Override
    public Entity create(Entity entity) throws ExceptionDao {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove entity from database.
     *
     * @param id - identity of removing entity.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(Object id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove entity from database.
     *
     * @param entity - entity which needed to be removed.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(Entity entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Update entity values.
     *
     * @param entity - entity which needed to be update
     *               with new params.
     * @return - entity with new params.
     */
    @Override
    public Entity update(Entity entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all entity from current data table.
     *
     * @return - list of entities which were found.
     * @throws ExceptionDao - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public List findAll() throws ExceptionDao {
        throw new UnsupportedOperationException();
    }

    /**
     * Find entity of target type by identity.
     *
     * @param id - identity of target entity.
     * @return - entity which corresponds to identity.
     */
    @Override
    public Entity findEntityById(Object id) throws ExceptionDao {
        throw new UnsupportedOperationException();
    }
}
