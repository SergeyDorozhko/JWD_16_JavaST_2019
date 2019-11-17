package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;

import java.sql.Connection;
import java.util.List;

public interface InterfaceDAO<K, T extends Entity> {

    /**
     * Method take connection to database and set it to realisation of Dao.
     *
     * @param connection - connection to database.
     */
    void setConnection(Connection connection);

    /**
     * Save new entity to database.
     *
     * @param entity -entity which will saved to database.
     * @return - result of saving. return true value if successfully.
     * @throws ExceptionDao - generating if some values conflicts
     *                      with existed in database.
     */
    boolean create(T entity) throws ExceptionDao;

    /**
     * Remove entity from database.
     *
     * @param id - identity of removing entity.
     * @return - true value if successfully removed, otherwise false.
     */
    boolean delete(K id);

    /**
     * Remove entity from database.
     *
     * @param entity - entity which needed to be removed.
     * @return - true value if successfully removed, otherwise false.
     */
    boolean delete(T entity);

    /**
     * Update entity values.
     *
     * @param entity - entity which needed to be update
     *               with new params.
     * @return - entity with new params.
     */
    T update(T entity);

    /**
     * Find all entity from current data table.
     *
     * @return - list of entities which were found.
     * @throws ExceptionDao - generated if some problems
     *                      with connecting to database.
     */
    List<T> findAll() throws ExceptionDao;

    /**
     * Find entity of target type by identity.
     * @param id - identity of target entity.
     * @return - entity which corresponds to identity.
     */
    T findEntityById(K id);
}
