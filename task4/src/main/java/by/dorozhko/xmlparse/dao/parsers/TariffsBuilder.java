package by.dorozhko.xmlparse.dao.parsers;

import by.dorozhko.xmlparse.entity.TariffType;

import java.util.HashSet;
import java.util.Set;

public abstract class TariffsBuilder {
    protected Set<TariffType> tariffsSet;

    public TariffsBuilder() {
        tariffsSet = new HashSet<>();
    }

    public  Set<TariffType> getTariffs(){
        return tariffsSet;
    }

    public abstract void buildSetTariffs(String fileName, String schemaPath);
}
