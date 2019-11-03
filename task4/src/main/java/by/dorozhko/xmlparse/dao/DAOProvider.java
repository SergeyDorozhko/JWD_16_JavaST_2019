package by.dorozhko.xmlparse.dao;

import by.dorozhko.xmlparse.dao.parsers.ParserFactory;
import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.dao.validator.XMLSchemaValidator;

public final class DAOProvider {
    /**
     * Single tone.
     */
    private static final DAOProvider INSTANCE = new DAOProvider();

    private DAOProvider() {
    }

    /**
     * creating link to validator.
     */
    private InterfaceValidator validator = new XMLSchemaValidator();

    /**
     * get link  to single object of this class.
     * @return link
     */
    public static DAOProvider getInstance() {
        return INSTANCE;
    }

    /**
     * get link to validator method.
     * @return link.
     */
    public InterfaceValidator getValidator() {
        return validator;
    }

    /**
     * get link of tariffs builder.
     * @param parser params for parsing (type of parser)
     * @return link to builder.
     */
    public TariffsBuilder getParser(final String parser) {
        ParserFactory parserFactory = ParserFactory.getInstance();

        return parserFactory.createTariffsBuilder(parser);
    }
}
