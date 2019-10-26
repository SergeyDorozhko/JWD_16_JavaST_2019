package by.dorozhko.xmlparse.parsers;

import by.dorozhko.xmlparse.parsers.dom.TariffsDOMBuilder;
import by.dorozhko.xmlparse.parsers.sax.TariffsSAXBuilder;
import by.dorozhko.xmlparse.parsers.stax.TariffsStAXBuilder;

public class ParserFactory {

    private static final ParserFactory instance = new ParserFactory();

    public static ParserFactory getInstance() {
        return instance;
    }

    public TariffsBuilder createTariffsBuilder(String typeParser){

        TariffsBuilder builder;

        switch (typeParser){
            case "DOM":
                builder = new TariffsDOMBuilder();
                break;
            case "SAX":
                builder = new TariffsSAXBuilder();
                break;
            case "StAX":
                builder = new TariffsStAXBuilder();
                break;
            default:
                throw new UnsupportedOperationException();

        }

        return builder;
    }
}
