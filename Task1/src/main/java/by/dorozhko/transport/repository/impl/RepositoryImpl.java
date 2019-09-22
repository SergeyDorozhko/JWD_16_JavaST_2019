package by.dorozhko.transport.repository.impl;

import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.TransportRepository;
import by.dorozhko.transport.repository.TransportSpecification;
import by.dorozhko.transport.repository.storage.Storage;

import java.util.List;

public class RepositoryImpl implements TransportRepository {

    /**
     * Repository method to add entity.
     *
     * @param entity to add.
     * @return is added.
     */
    @Override
    public boolean add(final TransportEntity entity) {
        Storage storage = Storage.getInstanse();
        return storage.add(entity);
    }

    /**
     * Method to remove oe entity.
     * @param removeId id of entity.
     * @return is deleted?
     */
    @Override
    public String delete(final int removeId) {
        Storage storage = Storage.getInstanse();

        return storage.deleteEntity(removeId);
    }

    /**
     * method to update entity.
     * @param transportEntity entity with actual information.
     * @return is updated?
     */
    @Override
    public boolean update(final TransportEntity transportEntity) {
        return false;
    }

    /**
     * Spetial query.
     * @param specification of query.
     * @return list of entity which correspond params.
     */
    @Override
    public List<TransportEntity> query(
            final TransportSpecification specification) {
        return specification.getAllEntityOfQuery();
    }

}
