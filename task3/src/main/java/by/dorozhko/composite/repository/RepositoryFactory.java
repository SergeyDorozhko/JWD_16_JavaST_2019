package by.dorozhko.composite.repository;

import by.dorozhko.composite.repository.impl.RepositoryImpl;

public final class RepositoryFactory {
    /**
     * single tone.
     */
    private static final RepositoryFactory INSTANCE
            = new RepositoryFactory();

    /**
     * single link to implementation.
     */
    private Repository repository = new RepositoryImpl();

    private RepositoryFactory() { }

    /**
     * get link t single object of class.
     * @return link.
     */
    public static RepositoryFactory getInstance() {
        return INSTANCE;
    }

    /**
     * get link to realisation.
     * @return link.
     */
    public Repository getRepository() {
        return repository;
    }
}
