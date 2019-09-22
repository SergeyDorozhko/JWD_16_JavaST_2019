package by.dorozhko.transport.repository.transport_specification.find_impl;

import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.storage.Storage;
import by.dorozhko.transport.repository.transport_specification.FindSpecification;

import java.util.List;

public class GetAllEntity implements FindSpecification {
    /**
     * connecting to the storage.
     *
     * @return list of entity.
     */
    public List<TransportEntity> getAllEntityOfQuery() {
        Storage storage = Storage.getInstanse();

        return storage.getAll();
    }
}
