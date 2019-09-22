package by.dorozhko.transport.repository;

import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllCarriages;
import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllEntity;
import by.dorozhko.transport.repository.transport_specification.sort_impl.TransportSortByName;
import by.dorozhko.transport.repository.transport_specification.sort_impl.TransportSortByWeight;
import by.dorozhko.transport.repository.transport_specification.sort_impl.comporators.TransportSortByNameThanByWeight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public final class SpecificationProvider {
    /**
     * logger.
     */
    private final Logger logger
            = LogManager.getLogger(SpecificationProvider.class.getName());

    /**
     * single tone.
     */
    private static final SpecificationProvider instance
            = new SpecificationProvider();

    /**
     * map of action and method.
     */
    private Map<String, TransportSpecification> specificationMap
            = new HashMap<>();

    private SpecificationProvider() {
        specificationMap.put("displayAll", new GetAllEntity());
        specificationMap.put("AllCarriages", new GetAllCarriages());
        specificationMap.put("SortAllByName", new TransportSortByName());
        specificationMap.put("SortAllByWeight", new TransportSortByWeight());
        specificationMap.put("SortAllByNameThenByWeight",
                new TransportSortByNameThanByWeight());

    }

    /**
     * get single obgect of method.
     *
     * @return provider.
     */
    public static SpecificationProvider getInstance() {
        return instance;
    }

    /**
     * Method connect query with the method.
     *
     * @param query user query.
     * @return specification to do needed action.
     */
    public TransportSpecification getSpecification(final String query) {
        logger.debug("Query: " + query);
        return specificationMap.get(query);
    }
}
