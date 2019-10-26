package by.dorozhko.xmlparse.parsers.sax;

import by.dorozhko.xmlparse.tariffs.TariffType;
import by.dorozhko.xmlparse.tariffs.VoiceTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class TariffsHandler extends DefaultHandler {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Set<TariffType> tariffTypeSet;

    private VoiceTariff currentTariff;

    private String tagName;

    public TariffsHandler() {
        tariffTypeSet = new HashSet<>();
    }

    public Set<TariffType> getTariffs() {
        return tariffTypeSet;
    }

    public void startElement(String uri, String localName, String qName, Attributes attr) {
        logger.debug("find tag: " + localName);
        if ("voice_tariff".equals(localName)) {
            logger.debug("create new tariff.");
            currentTariff = new VoiceTariff();
            if (attr.getLength() != 0) {
                logger.debug("set attribute payroll", attr.getValue(0));
                currentTariff.setPayroll(Double.parseDouble(attr.getValue(0)));
            } else {
                logger.debug("set default payroll");
                currentTariff.setPayroll(0.0);
            }
        } else if ("date".equals(localName)) {
            logger.debug("set launch date");
            currentTariff.getDate().setLaunchDate(attr.getValue(0));
            if (attr.getLength() == 2) {
                logger.debug("set archive date");
                currentTariff.getDate().setArchiveDate(attr.getValue(1));
            }
        } else {
            tagName = localName;
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("voice_tariff".equals(localName)) {
            logger.debug("end set params of tariff, add to  Set.");
            tariffTypeSet.add(currentTariff);
        }
    }


    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (tagName != null) {
            switch (tagName) {
                case "name":
                    logger.debug("set tariff name.");
                    currentTariff.setName(value);
                    break;
                case "operator_name":
                    logger.debug("set operator.");
                    currentTariff.setOperatorName(value);
                    break;
                case "in_operator":
                    logger.debug("set call price in operator.");
                    currentTariff.getCallPrice().setInOperator(Double.parseDouble(value));
                    break;
                case "other_operators":
                    logger.debug("set call price between operators.");
                    currentTariff.getCallPrice().setOtherOperators(Double.parseDouble(value));
                    break;
                case "sms_price":
                    logger.debug("set sms price.");
                    currentTariff.setSmsPrice(Double.parseDouble(value));
                    break;
                case "tarification":
                    logger.debug("set tarification type.");
                    currentTariff.getParametrs().setTarification(value);
                    break;
                case "connectiong":
                    logger.debug("set price of get sim-card.");
                    currentTariff.getParametrs().setConnectiong(Integer.parseInt(value));

                default:
                    logger.debug("not simple tag, nothing to set: ", tagName);
            }
        }

        tagName = null;

    }
}
