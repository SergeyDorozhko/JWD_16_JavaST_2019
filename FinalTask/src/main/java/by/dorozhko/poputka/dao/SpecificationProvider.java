package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.user.specificationImpl.ShowAllUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.HashMap;

public class SpecificationProvider {

    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private static final SpecificationProvider instance = new SpecificationProvider();

    private Map<String, QuerySpecification> specificationMap;

    private SpecificationProvider() {
        specificationMap = new HashMap<>();

        specificationMap.put("showAllUsers", new ShowAllUser());
    }

    public static SpecificationProvider getInstance(){
        return instance;
    }

    public QuerySpecification getSpecification(String query){
        logger.debug(query);
        return specificationMap.get(query);
    }
}
