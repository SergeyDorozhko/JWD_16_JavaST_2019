package by.dorozhko.transport.repository.impl;

import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.TransportRepository;
import by.dorozhko.transport.repository.TransportSpecification;
import by.dorozhko.transport.repository.storage.Storage;

import java.util.List;

public class RepositoryImpl implements TransportRepository {
    @Override
    public boolean add(final TransportEntity entity) {
        Storage storage = Storage.getInstanse();
        return storage.add(entity);
    }

    @Override
    public String delete(int removeId) {
        Storage storage = Storage.getInstanse();

        return storage.deleteEntity(removeId);
    }

    @Override
    public boolean update(TransportEntity transportEntity) {
        return false;
    }

    @Override
    public List<TransportEntity> query(final TransportSpecification specification) {
        return specification.getAllEntityOfQuery();
    }

}