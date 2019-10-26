package by.dorozhko.xmlparse.parsers;

import by.dorozhko.xmlparse.tariffs.TariffType;

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

    public abstract void buildSetTariffs(String fileName);
}
