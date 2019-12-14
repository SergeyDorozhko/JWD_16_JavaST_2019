package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddTripPage extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/addTrip.jsp";


    private static final String MAP_OF_COUNTRIES = "countriesMap";
    private static final String MAP_OF_CURRENCIES = "currenciesMap";
    private static final String MAP_OF_REGIONS_FROM = "regionFromMap";
    private static final String MAP_OF_REGIONS_TO = "regionToMap";
    private static final String MAP_OF_CITIES_FROM = "citiesFromMap";
    private static final String MAP_OF_CITIES_TO = "citiesToMap";


    private static final String USER_COUNTRY_FROM_ATTRIBUTE = "userCountryFrom";
    private static final String USER_REGION_FROM_ATTRIBUTE = "userRegionFrom";
    private static final String USER_CITY_FROM_ATTRIBUTE = "userCityFrom";
    private static final String USER_COUNTRY_TO_ATTRIBUTE = "userCountryTo";
    private static final String USER_REGION_TO_ATTRIBUTE = "userRegionTo";
    private static final String USER_CITY_TO_ATTRIBUTE = "userCityTo";
    private static final String USER_DEPARTURE_DATE_ATTRIBUTE = "userDepartureDate";
    private static final String USER_DEPARTURE_TIME_ATTRIBUTE = "userDepartureTime";
    private static final String USER_COST_ATTRIBUTE = "userCost";
    private static final String USER_CURRENCY_ATTRIBUTE = "userCurrency";
    private static final String USER_PASSENGERS_ATTRIBUTE = "userPassengers";
    private static final String USER_ADDITIONAL_INFORMATION_ATTRIBUTE = "userAdditionalInformation";


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


    private String userCountryFrom;
    private String userCountryTo;
    private String userRegionFrom;
    private String userRegionTo;


    private Set<String> listOfAttributes;

    public AddTripPage() {
        setAllowMethods(GET_METHOD);
        listOfAttributes = new HashSet<>();
        listOfAttributes.add(USER_COUNTRY_FROM_ATTRIBUTE);
        listOfAttributes.add(USER_REGION_FROM_ATTRIBUTE);
        listOfAttributes.add(USER_CITY_FROM_ATTRIBUTE);
        listOfAttributes.add(USER_COUNTRY_TO_ATTRIBUTE);
        listOfAttributes.add(USER_REGION_TO_ATTRIBUTE);
        listOfAttributes.add(USER_CITY_TO_ATTRIBUTE);
        listOfAttributes.add(USER_DEPARTURE_DATE_ATTRIBUTE);
        listOfAttributes.add(USER_DEPARTURE_TIME_ATTRIBUTE);
        listOfAttributes.add(USER_COST_ATTRIBUTE);
        listOfAttributes.add(USER_CURRENCY_ATTRIBUTE);
        listOfAttributes.add(USER_PASSENGERS_ATTRIBUTE);
        listOfAttributes.add(USER_ADDITIONAL_INFORMATION_ATTRIBUTE);
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

        attributesData(request);

        takeCatalogsData(request);

        return FORWARD_PAGE;
    }

    private void takeCatalogsData(final HttpServletRequest request) {
        resourceBundle = takeLocale(request);

        takeCountriesFromCatalog(request);
        takeCurrenciesFromCatalog(request);
        takeStartAddressRegionsOfCountryFromCatalog(request);
        takeStartAddressCitiesOfRegionFromCatalog(request);
        takeDestinationAddressRegionsOfCountryFromCatalog(request);
        takeDestinationAddressCitiesOfRegionFromCatalog(request);


    }

    private void takeCountriesFromCatalog(final HttpServletRequest request) {
        Map<Integer, String> countries = ServiceFactory.getInstance()
                .getCatalogService().getCountries();
        request.setAttribute(MAP_OF_COUNTRIES, countries);
    }

    private void takeCurrenciesFromCatalog(final HttpServletRequest request) {
        Map<Integer, String> currencies = ServiceFactory.getInstance()
                .getCatalogService().getCurrencies();
        request.setAttribute(MAP_OF_CURRENCIES, currencies);
    }

    private void takeStartAddressRegionsOfCountryFromCatalog(final HttpServletRequest request) {
        boolean isCountryKnown = userCountryFrom != null
                && userCountryFrom.length() != 0;
        if (isCountryKnown) {
            try {
                Map<Integer, String> regions = ServiceFactory.getInstance()
                        .getCatalogService().getRegionsOfCountry(
                                Integer.parseInt(userCountryFrom));
                request.setAttribute(MAP_OF_REGIONS_FROM, regions);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_COUNTRY_FROM_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }
    }

    private void takeStartAddressCitiesOfRegionFromCatalog(final HttpServletRequest request) {
        boolean isRegionKnown = userRegionFrom
                != null && userRegionFrom.length() != 0;
        if (isRegionKnown) {
            try {
                Map<Integer, String> cities = ServiceFactory.getInstance()
                        .getCatalogService().getCitiesOfRegion(
                                Integer.parseInt(userRegionFrom));
                request.setAttribute(MAP_OF_CITIES_FROM, cities);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_REGION_FROM_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }
    }

    private void takeDestinationAddressRegionsOfCountryFromCatalog(final HttpServletRequest request) {
        boolean isCountryKnown = userCountryTo != null
                && userCountryTo.length() != 0;
        if (isCountryKnown) {
            try {
                Map<Integer, String> regions = ServiceFactory.getInstance()
                        .getCatalogService().getRegionsOfCountry(
                                Integer.parseInt(userCountryTo));
                request.setAttribute(MAP_OF_REGIONS_TO, regions);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_COUNTRY_TO_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }
    }

    private void takeDestinationAddressCitiesOfRegionFromCatalog(final HttpServletRequest request) {
        boolean isRegionKnown = userRegionTo != null && userRegionTo.length() != 0;
        if (isRegionKnown) {
            try {
                Map<Integer, String> cities = ServiceFactory.getInstance()
                        .getCatalogService().getCitiesOfRegion(
                                Integer.parseInt(userRegionTo));
                request.setAttribute(MAP_OF_CITIES_TO, cities);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute(ERROR_REGION_TO_ATTRIBUTE,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
            }
        }
    }


    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        for (String attribute : listOfAttributes) {
            String attributeData =
                    (String) session.getAttribute(attribute);
            if (attributeData != null) {
                request.setAttribute(attribute, attributeData);
                session.removeAttribute(attribute);
                switch (attribute) {
                    case USER_COUNTRY_FROM_ATTRIBUTE:
                        userCountryFrom = attributeData;
                        break;
                    case USER_REGION_FROM_ATTRIBUTE:
                        userRegionFrom = attributeData;
                        break;
                    case USER_COUNTRY_TO_ATTRIBUTE:
                        userCountryTo = attributeData;
                        break;
                    case USER_REGION_TO_ATTRIBUTE:
                        userRegionTo = attributeData;
                        break;
                    default:
                        continue;
                }

            }
        }


    }
}