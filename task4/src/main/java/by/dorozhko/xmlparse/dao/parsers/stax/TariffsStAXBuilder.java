package by.dorozhko.xmlparse.dao.parsers.stax;

import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.entity.VoiceTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TariffsStAXBuilder extends TariffsBuilder {

    private final Logger logger = LogManager.getLogger(getClass().getName());


    private XMLInputFactory inputFactory;

    public TariffsStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        inputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);

    }



    public void buildSetTariffs(String fileName, String schemaPath) {

        String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
        Schema schema = null;
        try {
            schema = xsdFactory.newSchema(new File(schemaPath));

        } catch (SAXException e) {
            logger.error("error to create schema", e);
        }



        FileInputStream inputStream = null;
        XMLStreamReader streamReader = null;



        //TODO connect schema.

        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            streamReader = inputFactory.createXMLStreamReader(inputStream);



            while (streamReader.hasNext()) {
                int type = streamReader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = streamReader.getLocalName();
                    if ("voice_tariff".equals(name)) {
                        VoiceTariff tariff = buildTariff(streamReader);
                        tariffsSet.add(tariff);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            logger.error("StAX parser error: ", ex);
        } catch (IOException ex) {
            logger.error("IOException : ", ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                logger.error("Close file error", ex);
            }
        }
    }

    private VoiceTariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
        VoiceTariff tariff = new VoiceTariff();

        if (reader.getAttributeCount() != 0) {
            tariff.setPayroll(Double.parseDouble(reader.getAttributeValue(0)));
        } else {
            tariff.setPayroll(0.0);
        }

        String name;
        while (reader.hasNext()) {

            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name) {
                        case "name":
                            tariff.setName(getXMLText(reader));
                            break;
                        case "operator_name":
                            tariff.setOperatorName(getXMLText(reader));
                            break;
                        case "sms_price":
                            tariff.setSmsPrice(Double.parseDouble(getXMLText(reader)));
                            break;
                        case "date":
                            tariff.getDate().setLaunchDate(reader.getAttributeValue(0));
                            if (reader.getAttributeCount() == 2) {
                                tariff.getDate().setArchiveDate(reader.getAttributeValue(1));
                            }
                            break;
                        case "call_price":
                                tariff.setCallPrice(getXMLCallPrice(reader));
                            break;
                        case "parametrs":
                            tariff.setParametrs(getXMLParametrs(reader));
                            break;
                        default:
                            logger.debug("unsupported name", name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("voice_tariff")) {
                        return tariff;
                    }

                    break;
                default:
                    logger.debug("unsupported type", type);
            }

        }
        throw new XMLStreamException("Unknown element in tag voice_tariff");
    }

    private VoiceTariff.Parametrs getXMLParametrs(XMLStreamReader reader) throws XMLStreamException {
        VoiceTariff.Parametrs parametrs = new VoiceTariff.Parametrs();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name){
                        case "tarification":
                            parametrs.setTarification(getXMLText(reader));
                            break;
                        case "connectiong":
                            parametrs.setConnectiong(Integer.parseInt(getXMLText(reader)));
                            break;
                        default:
                            logger.debug("unsuppoted name", name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("parametrs")){
                        return parametrs;
                    }
                    break;
                default:
                    logger.debug("unsuppoted tag", type);
            }
        }


        throw new XMLStreamException("Unknown element in tag parametrs");
    }

    private VoiceTariff.CallPrice getXMLCallPrice(XMLStreamReader reader) throws XMLStreamException {
        VoiceTariff.CallPrice callPrice = new VoiceTariff.CallPrice();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name) {
                        case "in_operator":
                            callPrice.setInOperator(Double.parseDouble(getXMLText(reader)));
                            break;
                        case "other_operators":
                            callPrice.setOtherOperators(Double.parseDouble(getXMLText(reader)));
                            break;
                        default:
                            logger.debug("unsupported tag", name);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(name.equals("call_price")){
                        return callPrice;
                    }
                    break;
                default:
                    logger.debug("unsupported type", type);
            }
        }
        throw new XMLStreamException("Unknown element in tag call_price");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
