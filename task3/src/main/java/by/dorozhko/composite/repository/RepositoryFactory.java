package by.dorozhko.composite.repository;

import by.dorozhko.composite.repository.impl.RepositoryImpl;

public class RepositoryFactory {
    private static final RepositoryFactory instance = new RepositoryFactory();

    private Repository repository = new RepositoryImpl();

    private RepositoryFactory () { }

    public static RepositoryFactory getInstance() {
        return instance;
    }

    public Repository getRepository() {
        return repository;
    }
}
