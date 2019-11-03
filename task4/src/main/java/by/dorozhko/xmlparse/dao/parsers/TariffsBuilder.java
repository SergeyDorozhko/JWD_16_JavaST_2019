package by.dorozhko.xmlparse.dao.parsers;

import by.dorozhko.xmlparse.entity.TariffType;

import java.util.HashSet;
import java.util.Set;

public abstract class TariffsBuilder {
    /**
     * set of tariffs.
     */
    protected Set<TariffType> tariffsSet;

    /**
     * default constructor.
     */
    public TariffsBuilder() {
        tariffsSet = new HashSet<>();
    }

    /**
     * get set of tariffs method.
     * @return set.
     */
    public  Set<TariffType> getTariffs() {
        return tariffsSet;
    }

    /**
     * method do nothing, need to be realised in sub class.
     * @param fileName path to file.
     * @param schemaPath path to schema.
     */
    public abstract void buildSetTariffs(String fileName, String schemaPath);
}
