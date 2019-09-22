package by.dorozhko.transport.repository;

import by.dorozhko.transport.repository.impl.RepositoryImpl;
import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllEntity;

public final class RepositoryFactory {

    /**
     * single tone.
     */
    private static final RepositoryFactory instance = new RepositoryFactory();

    private RepositoryFactory() {
    }

    /**
     * Create repository.
     */
    private TransportRepository repository = new RepositoryImpl();

    /**
     * getting all entity.
     */
    private TransportSpecification specificationGetAll = new GetAllEntity();

    /**
     * Asking for single object of this class.
     *
     * @return single Repository Factory.
     */
    public static RepositoryFactory getInstance() {
        return instance;
    }

    /**
     * Method to get repository.
     * @return repository.
     */
    public TransportRepository getRepository() {
        return repository;
    }

    /**
     * Get all entity specification.
     * @return List of entity.
     */
    public TransportSpecification getSpecificationGetAll() {
        return specificationGetAll;
    }
}
