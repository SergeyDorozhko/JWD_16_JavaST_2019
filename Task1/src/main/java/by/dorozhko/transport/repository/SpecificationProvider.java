package by.dorozhko.transport.repository;

import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllCarriages;
import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SpecificationProvider {

    private final static Logger logger = LogManager.getLogger(SpecificationProvider.class.getName());

    private final static SpecificationProvider instance = new SpecificationProvider();

    private Map<String, TransportSpecification> specificationMap = new HashMap<>();

    private SpecificationProvider() {
        specificationMap.put("displayAll", new GetAllEntity());
        specificationMap.put("AllCarriages", new GetAllCarriages());
    }

    public static SpecificationProvider getInstance() {
        return instance;
    }

    public TransportSpecification getSpecification(String query) {
        logger.debug("Query: " + query);
        return specificationMap.get(query);
    }
}
