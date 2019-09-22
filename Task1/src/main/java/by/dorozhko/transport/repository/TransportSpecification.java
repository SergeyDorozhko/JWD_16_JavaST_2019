package by.dorozhko.transport.repository;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.List;

public interface TransportSpecification {
    /**
     * connecting to the storage by query.
     *
     * @return list of entity.
     */
    List<TransportEntity> getAllEntityOfQuery();
}
