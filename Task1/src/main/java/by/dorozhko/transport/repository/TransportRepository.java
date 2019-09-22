package by.dorozhko.transport.repository;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.List;

public interface TransportRepository {
    /**
     * Adding entity to repository.
     *
     * @param transportEntity new entity.
     * @return boolean result.
     */
    boolean add(TransportEntity transportEntity);

    /**
     * Delete entity.
     *
     * @param removeLine by value.
     * @return removed entity.
     */
    String delete(int removeLine);

    /**
     * Update entity params (NOT REALISED NOW).
     * @param transportEntity entity.
     * @return result.
     */
    boolean update(TransportEntity transportEntity);

    /**
     * Take entityes by query.
     * @param specification for query.
     * @return list of query.
     */
    List<TransportEntity> query(TransportSpecification specification);


}
