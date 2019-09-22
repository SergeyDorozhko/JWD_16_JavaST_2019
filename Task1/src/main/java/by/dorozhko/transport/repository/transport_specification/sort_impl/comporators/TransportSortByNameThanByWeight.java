package by.dorozhko.transport.repository.transport_specification.sort_impl.comporators;

import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.storage.Storage;
import by.dorozhko.transport.repository.transport_specification.SortSpecification;

import java.util.Comparator;
import java.util.List;

public class TransportSortByNameThanByWeight implements SortSpecification {
    /**
     * Method sort all entity by name then by weight.
     *
     * @return sorted list.
     */
    @Override
    public List<TransportEntity> getAllEntityOfQuery() {

        Storage storage = Storage.getInstanse();

        List<TransportEntity> allEntity = storage.getAll();

        allEntity.sort(Comparator.comparing(TransportEntity::getName).
                thenComparing(TransportEntity::getWeightInKilo));
        return allEntity;
    }
}
