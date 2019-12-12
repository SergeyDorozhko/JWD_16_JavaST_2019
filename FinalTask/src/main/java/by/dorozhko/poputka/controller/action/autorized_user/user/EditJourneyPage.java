package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class EditJourneyPage extends UserAction {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/editJourney.jsp";
    private static final String ERROR_FORWARD_PAGE = "/WEB-INF/jsp/error.jsp";

    private static final String MAP_OF_COUNTRIES = "countriesMap";
    private static final String MAP_OF_CURRENCIES = "currenciesMap";
    private static final String MAP_OF_REGIONS_FROM = "regionFromMap";
    private static final String MAP_OF_REGIONS_TO = "regionToMap";
    private static final String MAP_OF_CITIES_FROM = "citiesFromMap";
    private static final String MAP_OF_CITIES_TO = "citiesToMap";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String JOURNEY_ATTRIBUTE = "journey";


    private static final String JOURNEY_ID_ATTRIBUTE = "journeyId";

    private static final String ERROR_COUNTRY_FROM_ATTRIBUTE = "errorCountryFrom";
    private static final String ERROR_REGION_FROM_ATTRIBUTE = "errorRegionFrom";
    private static final String ERROR_CITY_FROM_ATTRIBUTE = "errorCityFrom";
    private static final String ERROR_COUNTRY_TO_ATTRIBUTE = "errorCountryTo";
    private static final String ERROR_REGION_TO_ATTRIBUTE = "errorRegionTo";
    private static final String ERROR_CITY_TO_ATTRIBUTE = "errorCityTo";
    private static final String ERROR_DEPARTURE_DATE_ATTRIBUTE = "errorDepartureDate";
    private static final String ERROR_DEPARTURE_TIME_ATTRIBUTE = "errorDepartureTime";
    private static final String ERROR_COST_ATTRIBUTE = "errorCost";
    private static final String ERROR_CURRENCY_ATTRIBUTE = "errorCurrency";
    private static final String ERROR_PASSENGERS_ATTRIBUTE = "errorPassengers";
    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";
    private static final String FIELD_FORMAT_ERROR_MESSAGE = "back.errors.fieldFormatError";
    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";

    private Set<String> listOfAttributes;

    private String journeyId;
    private int driverId;
    private HttpSession session;
    private Journey journey;

    public EditJourneyPage() {
        setAllowMethods(GET_METHOD);
        listOfAttributes = new HashSet<>();
        listOfAttributes.add(UNKNOWN_ERROR_ATTRIBUTE);
        listOfAttributes.add(ERROR_COUNTRY_FROM_ATTRIBUTE);
        listOfAttributes.add(ERROR_REGION_FROM_ATTRIBUTE);
        listOfAttributes.add(ERROR_CITY_FROM_ATTRIBUTE);
        listOfAttributes.add(ERROR_COUNTRY_TO_ATTRIBUTE);
        listOfAttributes.add(ERROR_REGION_TO_ATTRIBUTE);
        listOfAttributes.add(ERROR_CITY_TO_ATTRIBUTE);
        listOfAttributes.add(ERROR_DEPARTURE_DATE_ATTRIBUTE);
        listOfAttributes.add(ERROR_DEPARTURE_TIME_ATTRIBUTE);
        listOfAttributes.add(ERROR_COST_ATTRIBUTE);
        listOfAttributes.add(ERROR_CURRENCY_ATTRIBUTE);
        listOfAttributes.add(ERROR_PASSENGERS_ATTRIBUTE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);

        logger.debug("start method");
        getAttributesData(request);
        ResourceBundle resourceBundle = takeLocale(request);
        if (checkData(resourceBundle)) {
            if (journey == null) {
                JourneyService journeyService = ServiceFactory.getInstance().getJoureyService();
                journey = journeyService.findJourney(Integer.parseInt(journeyId), driverId);
            }
            logger.debug(journey);
            if (journey != null) {
                takeCatalogsData(request);

                request.setAttribute(JOURNEY_ATTRIBUTE, journey);
                session.setAttribute(JOURNEY_ID_ATTRIBUTE, journey.getId());
                return FORWARD_PAGE;
            }
        }

        session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE, resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
        return ERROR_FORWARD_PAGE;
    }

    private void getAttributesData(HttpServletRequest request) {
        session = request.getSession();
        User authorizedUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        driverId = authorizedUser.getId();

        journeyId = request.getParameter(JOURNEY_ID_ATTRIBUTE);
        if (journeyId == null) {
            journey = (Journey) session.getAttribute(JOURNEY_ATTRIBUTE);
            session.removeAttribute(JOURNEY_ATTRIBUTE);
            journeyId = Integer.toString((Integer) session.getAttribute(JOURNEY_ID_ATTRIBUTE));
        }


        for (String attribute : listOfAttributes) {
            String attributeData =
                    (String) session.getAttribute(attribute);
            if (attributeData != null) {
                request.setAttribute(attribute, attributeData);
                session.removeAttribute(attribute);
            }
        }
        logger.debug("data taken");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        int countErrors = 0;
        logger.debug(journeyId);
        if (journeyId == null || journeyId.length() == 0) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(journeyId);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        logger.debug(String.format("find errors : %d", countErrors));
        return countErrors == 0;
    }

    private void takeCatalogsData(final HttpServletRequest request) {
        ResourceBundle resourceBundle = takeLocale(request);

        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();
        request.setAttribute(MAP_OF_COUNTRIES, countries);
        Map<Integer, String> currencies = ServiceFactory.getInstance().getCatalogService().getCurrencies();
        request.setAttribute(MAP_OF_CURRENCIES, currencies);

        if (journey.getStartAddress().getCountry() != null && journey.getStartAddress().getCountry().length() != 0) {
            try {
                Map<Integer, String> regions = ServiceFactory.getInstance()
                        .getCatalogService().getRegionsOfCountry(
                                Integer.parseInt(journey.getStartAddress().getCountry()));
                request.setAttribute(MAP_OF_REGIONS_FROM, regions);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_COUNTRY_FROM_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }

        if (journey.getDestinationAddress().getCountry() != null && journey.getDestinationAddress().getCountry().length() != 0) {
            try {
                Map<Integer, String> regions = ServiceFactory.getInstance()
                        .getCatalogService().getRegionsOfCountry(
                                Integer.parseInt(journey.getDestinationAddress().getCountry()));
                request.setAttribute(MAP_OF_REGIONS_TO, regions);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_COUNTRY_TO_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }

        if (journey.getStartAddress().getRegionalCenter() != null && journey.getStartAddress().getRegionalCenter().length() != 0) {
            try {
                Map<Integer, String> cities = ServiceFactory.getInstance()
                        .getCatalogService().getCitiesOfRegion(
                                Integer.parseInt(journey.getStartAddress().getRegionalCenter()));
                request.setAttribute(MAP_OF_CITIES_FROM, cities);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_REGION_FROM_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }
        if (journey.getDestinationAddress().getRegionalCenter() != null && journey.getDestinationAddress().getRegionalCenter().length() != 0) {
            try {
                Map<Integer, String> cities = ServiceFactory.getInstance()
                        .getCatalogService().getCitiesOfRegion(
                                Integer.parseInt(journey.getDestinationAddress().getRegionalCenter()));
                request.setAttribute(MAP_OF_CITIES_TO, cities);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_REGION_TO_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }

    }


}
