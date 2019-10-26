package by.dorozhko.xmlparse.parsers.stax;

import by.dorozhko.xmlparse.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.tariffs.TariffType;
import by.dorozhko.xmlparse.tariffs.VoiceTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TariffsStAXBuilder extends TariffsBuilder {

    private final Logger logger = LogManager.getLogger(getClass().getName());

//    private Set<TariffType> tariffsSet;

    private XMLInputFactory inputFactory;

    public TariffsStAXBuilder() {
//        tariffsSet = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }

//    public Set<TariffType> getTariffs() {
//        return tariffsSet;
//    }


    public void buildSetTariffs(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader streamReader = null;
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
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("voice_tariff")) {
                        return tariff;
                    }

                    break;
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
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("parametrs")){
                        return parametrs;
                    }
                    break;
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
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(name.equals("call_price")){
                        return callPrice;
                    }
                    break;
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
