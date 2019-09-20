package by.dorozhko.transport.repository.transport_specification.find_impl;

import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.repository.storage.Storage;
import by.dorozhko.transport.repository.transport_specification.FindSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetAllCarriages implements FindSpecification {

    private final static Logger logger = LogManager.getLogger(GetAllCarriages.class.getName());

    public List<TransportEntity> getAllEntityOfQuery() {
        logger.debug("start method");
        Storage storage = Storage.getInstanse();

        List<TransportEntity> allEntity = storage.getAll();

        List<TransportEntity> onlyCarriages = new ArrayList<>();

        for (TransportEntity entity : allEntity) {
            boolean isCarriage = entity.getClass().getName().equals(Carriage.class.getName());
            logger.trace("Is a carriage: " + isCarriage);
            if (isCarriage) {
                onlyCarriages.add(entity);
            }
        }

        return onlyCarriages;
    }
}
