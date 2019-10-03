package by.dorozhko.matrix.repository.storage;

import by.dorozhko.matrix.repository.storage.specificationImpl.*;

import java.util.HashMap;
import java.util.Map;

public class SpecificationProvider {

    private static final SpecificationProvider instance = new SpecificationProvider();

    private Map<String, StorageSpecification> specificationMap = new HashMap<>();

    private SpecificationProvider(){
        specificationMap.put("ReentrantLock", new SetWithReentrantLock());
        specificationMap.put("Semaphore", new SetWithSemaphore());
        specificationMap.put("Phase", new SetWithTimeUnit());
    }

    public static SpecificationProvider getInstance(){
        return instance;
    }


    public StorageSpecification getSpecification (String specification){
        return specificationMap.get(specification);
    }
}
