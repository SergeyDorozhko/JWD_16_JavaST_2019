package by.dorozhko.xmlparse.parsers.sax;

import by.dorozhko.xmlparse.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.parsers.sax.TariffsHandler;
import by.dorozhko.xmlparse.tariffs.TariffType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class TariffsSAXBuilder extends TariffsBuilder {

    private final Logger logger = LogManager.getLogger(getClass().getName());

 //   private Set<TariffType> tariffsSet;

    private TariffsHandler handler;

    private XMLReader reader;

    public TariffsSAXBuilder() {
        handler = new TariffsHandler();

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException ex) {
            logger.error("SAX Parser exception", ex);
        }
    }

//    public Set<TariffType> getTariffs(){
//        return tariffsSet;
//    }

    public void buildSetTariffs(String fileName){
        try {
            reader.parse(fileName);
        }catch (SAXException ex){
            logger.error("SAX Parser exception", ex);
        }catch (IOException ex){
            logger.error("IO Exception", ex);
        }

        tariffsSet = handler.getTariffs();
    }
}
