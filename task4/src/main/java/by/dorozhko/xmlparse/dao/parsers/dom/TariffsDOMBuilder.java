package by.dorozhko.xmlparse.dao.parsers.dom;

import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.entity.TariffType;
import by.dorozhko.xmlparse.entity.VoiceTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class TariffsDOMBuilder extends TariffsBuilder {

    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Defines the API to obtain DOM Document instances from an
     * XML document. Using this class, an application programmer
     * can obtain a Document from XML.
     */
    private DocumentBuilder documentBuilder;

    /**
     * build set of entity from xml method.
     * @param filePath path to file.
     * @param schemaPath path to schema.
     */
    public void buildSetTariffs(final String filePath,
                                final String schemaPath) {

        DocumentBuilderFactory documentBuilderFactory
                = DocumentBuilderFactory.newInstance();

        String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
        Schema schema = null;
        try {
            schema = xsdFactory.newSchema(new File(schemaPath));

        } catch (SAXException e) {
            logger.error("error to create schema", e);
        }


        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setSchema(schema);


        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            logger.error("error config parser", ex);
        }


        Document document = null;
        try {
            document = documentBuilder.parse(filePath);
            Element root = document.getDocumentElement();

            NodeList tariffList = root.getElementsByTagName("voice_tariff");

            for (int i = 0; i < tariffList.getLength(); i++) {
                Element tariffElement = (Element) tariffList.item(i);
                TariffType voiceTariff = builtTariff(tariffElement);
                tariffsSet.add(voiceTariff);
            }


        } catch (IOException ex) {
            logger.error("ioexception", ex);
        } catch (SAXException ex) {
            logger.error("parsing fail", ex);
        }
    }


    private TariffType builtTariff(final Element tariffElement) {
        VoiceTariff tariff = new VoiceTariff();
        tariff.setName(getElementTextContent(tariffElement, "name"));
        tariff.setOperatorName(getElementTextContent(tariffElement,
                "operator_name"));
        tariff.setPayroll(Double.parseDouble(tariffElement
                .getAttribute("payroll")));
        tariff.setSmsPrice(Double.parseDouble(
                getElementTextContent(tariffElement, "sms_price")));
        VoiceTariff.CallPrice callPrice = tariff.getCallPrice();
        Element callPriceElement = (Element) tariffElement
                .getElementsByTagName("call_price").item(0);

        callPrice.setInOperator(Double.valueOf(
                getElementTextContent(callPriceElement,
                        "in_operator")));
        callPrice.setOtherOperators(Double.parseDouble(
                getElementTextContent(callPriceElement,
                        "other_operators")));

        VoiceTariff.Parametrs params = tariff.getParametrs();
        Element paramElement = (Element) tariffElement
                .getElementsByTagName("parametrs").item(0);
        params.setConnectiong(Integer.parseInt(getElementTextContent(
                paramElement, "connectiong")));
        params.setTarification(getElementTextContent(paramElement,
                "tarification"));


        VoiceTariff.Date dateTariff = tariff.getDate();
        Element dateElement = (Element) tariffElement
                .getElementsByTagName("date").item(0);
        dateTariff.setLaunchDate(dateElement.getAttribute("launch_date"));
        dateTariff.setArchiveDate(dateElement.getAttribute("archive_date"));
        return tariff;
    }

    private static String getElementTextContent(final Element element,
                                                final String elementName) {
        NodeList list = element.getElementsByTagName(elementName);
        Node node = list.item(0);
        return node.getTextContent();
    }


}
