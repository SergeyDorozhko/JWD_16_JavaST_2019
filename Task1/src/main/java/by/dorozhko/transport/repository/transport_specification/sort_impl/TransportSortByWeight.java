package by.dorozhko.transport.repository.transport_specification.sort_impl;

import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.storage.Storage;
import by.dorozhko.transport.repository.transport_specification.SortSpecification;
import by.dorozhko.transport.repository.transport_specification.sort_impl.comporators.TransportWeightComporator;

import java.util.Collections;
import java.util.List;

public class TransportSortByWeight implements SortSpecification {
    /**
     * sort list by weight.
     *
     * @return sorted list.
     */
    @Override
    public List<TransportEntity> getAllEntityOfQuery() {

        Storage storage = Storage.getInstanse();

        List<TransportEntity> allEntity = storage.getAll();
        Collections.sort(allEntity, new TransportWeightComporator());
        return allEntity;
    }
}
