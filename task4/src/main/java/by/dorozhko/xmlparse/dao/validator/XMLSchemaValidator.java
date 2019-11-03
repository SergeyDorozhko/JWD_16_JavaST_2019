package by.dorozhko.xmlparse.dao.validator;

import by.dorozhko.xmlparse.dao.InterfaceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLSchemaValidator implements InterfaceValidator {
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger();

    /**
     * validating xml file by schema.
     * @param pathToXML path to xml file on server.
     * @param pathToXSD path to xsd file on server.
     * @return result of validating.
     */
    public boolean validate(final String pathToXML, final String pathToXSD) {
        logger.debug("start validation");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory
                .IS_SUPPORTING_EXTERNAL_ENTITIES, false);

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);

        File schemaLocation = new File(pathToXSD);

        try {
            Schema schema = schemaFactory.newSchema(schemaLocation);

            Validator validator = schema.newValidator();
            Source source = new StreamSource(pathToXML);
            validator.validate(source);
            return true;
        } catch (SAXException ex) {
            logger.error("not valid XML, because:", ex);
            return false;
        } catch (IOException ex) {
            logger.error("not valid because: ", ex);
            return false;
        }
    }
}
