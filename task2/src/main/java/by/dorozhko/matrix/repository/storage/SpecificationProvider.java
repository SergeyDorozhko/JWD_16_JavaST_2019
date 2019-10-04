package by.dorozhko.matrix.repository.storage;

import by.dorozhko.matrix.repository.storage.specification.SetWithReentrantLock;
import by.dorozhko.matrix.repository.storage.specification.SetWithSemaphore;
import by.dorozhko.matrix.repository.storage.specification.SetWithTimeUnit;


import java.util.HashMap;
import java.util.Map;

public final class SpecificationProvider {
    /**
     * single tone.
     */
    private static final SpecificationProvider instance
            = new SpecificationProvider();
    /**
     * Map of specifications, depends from chosen type of synchronization.
     */
    private Map<String, StorageSpecification> specificationMap
            = new HashMap<>();

    private SpecificationProvider() {
        specificationMap.put("ReentrantLock", new SetWithReentrantLock());
        specificationMap.put("Semaphore", new SetWithSemaphore());
        specificationMap.put("TimeUnit", new SetWithTimeUnit());
    }

    /**
     * get link to single object of this class.
     * @return link.
     */
    public static SpecificationProvider getInstance() {
        return instance;
    }

    /**
     * get type of synchronization by query.
     * @param specification query to method of setup.
     * @return link to method.
     */
    public StorageSpecification getSpecification(final String specification) {
        return specificationMap.get(specification);
    }
}
