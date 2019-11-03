package by.dorozhko.xmlparse.dao.parsers;

import by.dorozhko.xmlparse.dao.parsers.stax.TariffsStAXBuilder;
import by.dorozhko.xmlparse.dao.parsers.dom.TariffsDOMBuilder;
import by.dorozhko.xmlparse.dao.parsers.sax.TariffsSAXBuilder;

public class ParserFactory {
    /**
     * Single tone.
     */
    private static final ParserFactory INSTANCE = new ParserFactory();

    /**
     * get link to single object of this class.
     *
     * @return link.
     */
    public static ParserFactory getInstance() {
        return INSTANCE;
    }

    /**
     * chose needed type of parser.
     *
     * @param typeParser type of needed parser.
     * @return link to the parser.
     */
    public TariffsBuilder createTariffsBuilder(final String typeParser) {

        TariffsBuilder builder;

        switch (typeParser) {
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
