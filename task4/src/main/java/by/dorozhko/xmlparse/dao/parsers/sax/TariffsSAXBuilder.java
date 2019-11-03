package by.dorozhko.xmlparse.dao.parsers.sax;

import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class TariffsSAXBuilder extends TariffsBuilder {

    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * tariff haandler.
     */
    private TariffsHandler handler;

    /**
     * Interface for reading an XML document using callbacks.
     */
    private XMLReader reader;

    /**
     * default constructor.
     */
    public TariffsSAXBuilder() {
        handler = new TariffsHandler();

    }

    /**
     * build set from xml.
     * @param fileName path to file.
     * @param schemaPath path to schema.
     */
    public void buildSetTariffs(final String fileName,
                                final String schemaPath) {
        String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
        Schema schema = null;
        try {
            schema = xsdFactory.newSchema(new File(schemaPath));

        } catch (SAXException e) {
            logger.error("error to create schema", e);
        }


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.setValidating(false);
        saxParserFactory.setSchema(schema);


        try {
            reader = saxParserFactory.newSAXParser().getXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException | ParserConfigurationException ex) {
            logger.error("SAX Parser exception", ex);
        }


        try {
            reader.parse(fileName);
        } catch (SAXException ex) {
            logger.error("SAX Parser exception", ex);
        } catch (IOException ex) {
            logger.error("IO Exception", ex);
        }

        tariffsSet = handler.getTariffs();
    }
}
