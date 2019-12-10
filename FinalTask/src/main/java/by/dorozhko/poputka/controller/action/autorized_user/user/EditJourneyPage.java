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
import java.util.Map;
import java.util.ResourceBundle;

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

    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";
    private static final String FIELD_FORMAT_ERROR_MESSAGE = "back.errors.fieldFormatError";
    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";


    private String journeyId;
    private int driverId;
    private HttpSession session;
    private Journey journey;

    public EditJourneyPage() {
        setAllowMethods(GET_METHOD);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        getAttributesData(request);
        ResourceBundle resourceBundle = takeLocale(request);
        if (checkData(resourceBundle)) {

            JourneyService journeyService = ServiceFactory.getInstance().getJoureyService();
            journey = journeyService.findJourney(Integer.parseInt(journeyId), driverId);
            logger.debug(journey);
            if (journey != null) {
                takeCatalogsData(request);
                request.setAttribute(JOURNEY_ATTRIBUTE, journey);
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


    }

    private boolean checkData(ResourceBundle resourceBundle) {
        int countErrors = 0;
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
        return countErrors == 0;
    }

    private void takeCatalogsData(final HttpServletRequest request) {

        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();
        request.setAttribute(MAP_OF_COUNTRIES, countries);
        Map<Integer, String> currencies = ServiceFactory.getInstance().getCatalogService().getCurrencies();
        request.setAttribute(MAP_OF_CURRENCIES, currencies);


        Map<Integer, String> regionsFrom = ServiceFactory.getInstance()
                .getCatalogService().getRegionsOfCountry(
                        Integer.parseInt(journey.getStartAddress().getCountry()));
        request.setAttribute(MAP_OF_REGIONS_FROM, regionsFrom);


        Map<Integer, String> regionsTo = ServiceFactory.getInstance()
                .getCatalogService().getRegionsOfCountry(
                        Integer.parseInt(journey.getDestinationAddress().getCountry()));
        request.setAttribute(MAP_OF_REGIONS_TO, regionsTo);


        Map<Integer, String> citiesFrom = ServiceFactory.getInstance()
                .getCatalogService().getCitiesOfRegion(
                        Integer.parseInt(journey.getStartAddress().getRegionalCenter()));
        request.setAttribute(MAP_OF_CITIES_FROM, citiesFrom);


        Map<Integer, String> citiesTo = ServiceFactory.getInstance()
                .getCatalogService().getCitiesOfRegion(
                        Integer.parseInt(journey.getDestinationAddress().getRegionalCenter()));
        request.setAttribute(MAP_OF_CITIES_TO, citiesTo);

    }


}
