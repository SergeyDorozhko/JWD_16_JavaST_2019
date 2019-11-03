package by.dorozhko.xmlparse.dao.parsers.sax;

import by.dorozhko.xmlparse.entity.TariffType;
import by.dorozhko.xmlparse.entity.VoiceTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class TariffsHandler extends DefaultHandler {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Set<TariffType> tariffsSet;

    private VoiceTariff currentTariff;

    private String tagName;

    public TariffsHandler() {
        tariffsSet = new HashSet<>();
    }

    public Set<TariffType> getTariffs() {
        return tariffsSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        logger.debug("find tag: ", localName);
        if ("voice_tariff".equals(localName)) {
            logger.debug("create new tariff.");
            currentTariff = new VoiceTariff();

            currentTariff.setPayroll(Double.parseDouble(attr.getValue(0)));

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

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("voice_tariff".equals(localName)) {
            logger.debug("end set params of tariff, add to  Set.");
            tariffsSet.add(currentTariff);
        }
    }

    @Override
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
                    break;
                default:
                    logger.debug("not simple tag, nothing to set: ", tagName);
            }
        }

        tagName = null;

    }
}
