package by.dorozhko.transport.repository;

import by.dorozhko.transport.repository.impl.RepositoryImpl;
import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllEntity;

public class RepositoryFactory {
    private static final RepositoryFactory instance = new RepositoryFactory();

    private RepositoryFactory() {
    }

    private TransportRepository repository = new RepositoryImpl();
    private TransportSpecification specificationGetAll = new GetAllEntity();

    public static RepositoryFactory getInstance() {
        return instance;
    }

    public TransportRepository getRepository() {
        return repository;
    }

    public TransportSpecification getSpecificationGetAll() {
        return specificationGetAll;
    }
}
