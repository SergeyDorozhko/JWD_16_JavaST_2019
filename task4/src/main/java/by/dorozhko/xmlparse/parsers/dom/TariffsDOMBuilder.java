package by.dorozhko.xmlparse.parsers.dom;

import by.dorozhko.xmlparse.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.tariffs.TariffType;
import by.dorozhko.xmlparse.tariffs.VoiceTariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TariffsDOMBuilder extends TariffsBuilder {
    private final Logger logger = LogManager.getLogger(getClass().getName());

 //   private Set<TariffType> tariffsSet;

    private DocumentBuilder documentBuilder;

    public TariffsDOMBuilder() {
//        tariffsSet = new HashSet<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            logger.error("error config parser", ex);
        }

    }


//    public Set<TariffType> getTariffs() {
//        return tariffsSet;
//    }

    public void buildSetTariffs(String filePath) {
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


    private TariffType builtTariff(Element tariffElement) {
        VoiceTariff tariff = new VoiceTariff();
        tariff.setName(getElementTextContent(tariffElement, "name"));
        tariff.setOperatorName(getElementTextContent(tariffElement, "operator_name"));
        if (tariffElement.hasAttribute("payroll")) {
            tariff.setPayroll(Double.parseDouble(tariffElement.getAttribute("payroll")));
        } else {
            tariff.setPayroll(0.0);
        }
        tariff.setSmsPrice(Double.parseDouble(getElementTextContent(tariffElement, "sms_price")));
        VoiceTariff.CallPrice callPrice = tariff.getCallPrice();
        Element callPriceElement = (Element) tariffElement.getElementsByTagName("call_price").item(0);

        callPrice.setInOperator(Double.valueOf(getElementTextContent(callPriceElement, "in_operator")));
        callPrice.setOtherOperators(Double.parseDouble(getElementTextContent(callPriceElement, "other_operators")));

        VoiceTariff.Parametrs params = tariff.getParametrs();
        Element paramElement = (Element) tariffElement.getElementsByTagName("parametrs").item(0);
        params.setConnectiong(Integer.parseInt(getElementTextContent(paramElement, "connectiong")));
        params.setTarification(getElementTextContent(paramElement, "tarification"));


        VoiceTariff.Date dateTariff = tariff.getDate();
        Element dateElement = (Element) tariffElement.getElementsByTagName("date").item(0);
        dateTariff.setLaunchDate(dateElement.getAttribute("launch_date"));
        dateTariff.setArchiveDate(dateElement.getAttribute("archive_date"));
        return tariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList list = element.getElementsByTagName(elementName);
        Node node = list.item(0);
        String text = node.getTextContent();


        return text;
    }


}
