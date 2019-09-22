package by.dorozhko.transport.repository.transport_specification.sort_impl;

import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.storage.Storage;
import by.dorozhko.transport.repository.transport_specification.SortSpecification;
import by.dorozhko.transport.repository.transport_specification.sort_impl.comporators.TransportNameComporator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class TransportSortByName implements SortSpecification {
    /**
     * logger.
     */
    private final Logger logger =
            LogManager.getLogger(TransportSortByName.class.getName());

    /**
     * sort all entity.
     *
     * @return sorted list.
     */
    @Override
    public List<TransportEntity> getAllEntityOfQuery() {
        logger.debug("sort by name");
        Storage storage = Storage.getInstanse();

        List<TransportEntity> allEntity = storage.getAll();
        Collections.sort(allEntity, new TransportNameComporator());
        return allEntity;
    }
}
