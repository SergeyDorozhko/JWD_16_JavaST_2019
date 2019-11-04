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
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * set of tariffs from file.
     */
    private Set<TariffType> tariffsSet;
    /**
     * entity for creating tariff.
     */
    private VoiceTariff currentTariff;
    /**
     * tag name.
     */
    private String tagName;

    /**
     * public default constructor, creates hashSet of tariffs.
     */
    public TariffsHandler() {
        tariffsSet = new HashSet<>();
    }

    /**
     * get set of tariffs.
     *
     * @return set.
     */
    public Set<TariffType> getTariffs() {
        return tariffsSet;
    }

    /**
     * take specific actions at the start of each element
     * (such as allocating a new tree node).
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is
     *                  not being performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not
     *                  available.
     * @param attr      The attributes attached to the element.
     *                  If there are no attributes, it shall
     *                  be an empty Attributes object.
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attr) {
        logger.debug(localName, "find tag: ");
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

    /**
     * take specific actions at the end of each element
     * (such as finalising a tree node).
     *
     * @param uri       The Namespace URI, or the empty string
     *                  if the element has no Namespace URI or
     *                  if Namespace processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is
     *                  not being performed.
     * @param qName     The qualified name (with prefix), or the empty
     *                  string if qualified names are not available.
     */
    @Override
    public void endElement(final String uri,
                           final String localName,
                           final String qName) {
        if ("voice_tariff".equals(localName)) {
            logger.debug("end set params of tariff, add to  Set.");
            tariffsSet.add(currentTariff);
        }
    }

    /**
     * Receive notification of character data.
     * The Parser will call this method to report each chunk of character
     * data. SAX parsers may return all contiguous character data in a single
     * chunk, or they may split it into several chunks; however, all of the
     * characters in any single event must come from the same external entity
     * so that the Locator provides useful information.
     * <p>
     * The application must not attempt to read from the array outside of the
     * specified range.
     *
     * @param ch     the characters from the XML document.
     * @param start  the start position in the array.
     * @param length the number of characters to read from the array
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
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
                    currentTariff.getCallPrice()
                            .setInOperator(Double.parseDouble(value));
                    break;
                case "other_operators":
                    logger.debug("set call price between operators.");
                    currentTariff.getCallPrice()
                            .setOtherOperators(Double.parseDouble(value));
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
                    currentTariff.getParametrs()
                            .setConnectiong(Integer.parseInt(value));
                    break;
                default:
                    logger.debug(tagName, "not simple tag, nothing to set: ");
            }
        }

        tagName = null;

    }
}
