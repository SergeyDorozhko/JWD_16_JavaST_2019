package by.dorozhko.composite.repository;

import by.dorozhko.composite.repository.impl.RepositoryImpl;

public final class RepositoryFactory {
    private static final RepositoryFactory INSTANCE
            = new RepositoryFactory();

    private Repository repository = new RepositoryImpl();

    private RepositoryFactory() {
    }

    public static RepositoryFactory getInstance() {
        return INSTANCE;
    }

    public Repository getRepository() {
        return repository;
    }
}
