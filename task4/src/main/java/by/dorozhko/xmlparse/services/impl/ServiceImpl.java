package by.dorozhko.xmlparse.services.impl;

import by.dorozhko.xmlparse.parsers.dom.TariffsDOMBuilder;
import by.dorozhko.xmlparse.parsers.sax.TariffsSAXBuilder;
import by.dorozhko.xmlparse.services.Service;
import by.dorozhko.xmlparse.tariffs.TariffType;

import java.util.Set;

public class ServiceImpl implements Service {
    @Override
    public String parseDOM(String pathToXMLandXSD) {
        TariffsDOMBuilder builder = new TariffsDOMBuilder();
        builder.builtSetTarrifs(pathToXMLandXSD);
        Set<TariffType> list = builder.getTariffs();
        StringBuilder result = new StringBuilder();
        for (TariffType tariff : list) {
            result.append(tariff);
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String parseSAX(String pathToXMLandXSD) {
        TariffsSAXBuilder builder = new TariffsSAXBuilder();
        builder.buildSetTariffs(pathToXMLandXSD);
        Set<TariffType> list = builder.getTariffs();
        StringBuilder result = new StringBuilder();
        for (TariffType tariff : list) {
            result.append(tariff);
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String parseStAX(String pathToXMLandXSD) {
        return null;
    }
}
