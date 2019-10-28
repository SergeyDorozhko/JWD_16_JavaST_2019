package by.dorozhko.xmlparse.services.impl;

import by.dorozhko.xmlparse.parsers.ParserFactory;
import by.dorozhko.xmlparse.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.services.Service;
import by.dorozhko.xmlparse.tariffs.TariffType;
import by.dorozhko.xmlparse.validator.XMLSchemaValidator;

import java.util.Set;

public class ServiceImpl implements Service {
    ParserFactory parserFactory = ParserFactory.getInstance();

    @Override
    public String buildTariffs(String pathToXMLandXSD) {

        if(!XMLSchemaValidator.validate(pathToXMLandXSD.split("-")[2],pathToXMLandXSD.split("-")[3])){
            return "NOT VALID XML FILE, check schema and your .xml file";
        }


        TariffsBuilder builder = parserFactory.createTariffsBuilder(pathToXMLandXSD.split("-")[1]);

        builder.buildSetTariffs(pathToXMLandXSD.split("-")[2]);
        Set<TariffType> list = builder.getTariffs();
        StringBuilder result = new StringBuilder();
        for (TariffType tariff : list) {
            result.append(tariff);
            result.append("\n");
        }
        return result.toString();
    }
}