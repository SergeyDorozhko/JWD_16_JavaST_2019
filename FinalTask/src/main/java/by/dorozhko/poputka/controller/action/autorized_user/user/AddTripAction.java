package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.Address;
import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AddTripAction extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SUCCESSFUL_REDIRECT_URL = "/listOfJourneys.html";
    private static final String ERROR_REDIRECT_URL = "/addTrip.html";

    private static final String BUTTON = "button";
    private static final String ADD_JOURNEY_BUTTON = "add";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String COUNTRY_FROM_ATTRIBUTE = "countryFrom";
    private static final String REGION_FROM_ATTRIBUTE = "regionFrom";
    private static final String CITY_FROM_ATTRIBUTE = "cityFrom";
    private static final String COUNTRY_TO_ATTRIBUTE = "countryTo";
    private static final String REGION_TO_ATTRIBUTE = "regionTo";
    private static final String CITY_TO_ATTRIBUTE = "cityTo";
    private static final String DEPARTURE_DATE_ATTRIBUTE = "departureDate";
    private static final String DEPARTURE_TIME_ATTRIBUTE = "departureTime";
    private static final String COST_ATTRIBUTE = "cost";
    private static final String CURRENCY_ATTRIBUTE = "currency";
    private static final String PASSENGERS_ATTRIBUTE = "passengers";
    private static final String ADDITIONAL_INFORMATION_ATTRIBUTE = "additionalInformation";


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

    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";
    private static final String FIELD_IS_EMPTY_ERROR_MESSAGE = "back.errors.fieldIsEmptyError";
    private static final String FIELD_FORMAT_ERROR_MESSAGE = "back.errors.fieldFormatError";

    private HttpSession session;

    private String countryFrom;
    private String regionFrom;
    private String cityFrom;
    private String countryTo;
    private String regionTo;
    private String cityTo;
    private String departureDate;
    private String departureTime;
    private String cost;
    private String currency;
    private String passengers;
    private String additionalInformation;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        getAllAttributesData(request);

        ResourceBundle resourceBundle = takeLocale(request);

        String button = request.getParameter(BUTTON);
        boolean isSaveActionAndValid = button != null && button.equals(ADD_JOURNEY_BUTTON) && checkData(resourceBundle);

        if (isSaveActionAndValid) {
            JourneyService journeyService = ServiceFactory.getInstance().getJoureyService();
            User user = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
            User driverId = new User();
            driverId.setId(user.getId());
            Address startAddress = new Address(countryFrom, regionFrom, cityFrom);
            Address destinationAddress = new Address(countryTo, regionTo, cityTo);
            Journey journey = new Journey(driverId, startAddress,
                    destinationAddress, departureTime, departureDate,
                    Double.parseDouble(cost), currency,
                    Integer.parseInt(passengers), additionalInformation);
            logger.debug(String.format("creating journey: %s", journey.getDriver().getId()));
            try {
                journeyService.createNewJourney(journey);

                return request.getContextPath() + SUCCESSFUL_REDIRECT_URL;

            } catch (ExceptionService exceptionService) {
                logger.error(exceptionService);
                session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE, resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
            }
        }

        setUserInputData();
        return request.getContextPath() + ERROR_REDIRECT_URL;
    }

    private void getAllAttributesData(HttpServletRequest request) {
        countryFrom = request.getParameter(COUNTRY_FROM_ATTRIBUTE);
        regionFrom = request.getParameter(REGION_FROM_ATTRIBUTE);
        cityFrom = request.getParameter(CITY_FROM_ATTRIBUTE);
        countryTo = request.getParameter(COUNTRY_TO_ATTRIBUTE);
        regionTo = request.getParameter(REGION_TO_ATTRIBUTE);
        cityTo = request.getParameter(CITY_TO_ATTRIBUTE);
        departureDate = request.getParameter(DEPARTURE_DATE_ATTRIBUTE);
        departureTime = request.getParameter(DEPARTURE_TIME_ATTRIBUTE);
        cost = request.getParameter(COST_ATTRIBUTE);
        currency = request.getParameter(CURRENCY_ATTRIBUTE);
        passengers = request.getParameter(PASSENGERS_ATTRIBUTE);
        additionalInformation = request.getParameter(ADDITIONAL_INFORMATION_ATTRIBUTE);
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");
        int countErrors = 0;

        if (countryFrom.length() == 0) {
            session.setAttribute(ERROR_COUNTRY_FROM_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(countryFrom);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_COUNTRY_FROM_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        if (regionFrom.length() == 0) {
            session.setAttribute(ERROR_REGION_FROM_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(regionFrom);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_REGION_FROM_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (cityFrom.length() == 0) {
            session.setAttribute(ERROR_CITY_FROM_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(cityFrom);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_CITY_FROM_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (countryTo.length() == 0) {
            session.setAttribute(ERROR_COUNTRY_TO_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(countryTo);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_COUNTRY_TO_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (regionTo.length() == 0) {
            session.setAttribute(ERROR_REGION_TO_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(regionTo);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_REGION_TO_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (cityTo.length() == 0) {
            session.setAttribute(ERROR_CITY_TO_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(cityTo);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_CITY_TO_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (departureDate.length() == 0) {
            session.setAttribute(ERROR_DEPARTURE_DATE_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalDate.parse(departureDate);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_DEPARTURE_DATE_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (departureTime.length() == 0) {
            session.setAttribute(ERROR_DEPARTURE_TIME_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalTime.parse(departureTime);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_DEPARTURE_TIME_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (cost.length() == 0) {
            session.setAttribute(ERROR_COST_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Double.parseDouble(cost);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_COST_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (currency.length() == 0) {
            session.setAttribute(ERROR_CURRENCY_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(currency);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_CURRENCY_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (passengers.length() == 0) {
            session.setAttribute(ERROR_PASSENGERS_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(passengers);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_PASSENGERS_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void setUserInputData() {
        session.setAttribute(USER_COUNTRY_FROM_ATTRIBUTE, countryFrom);
        session.setAttribute(USER_REGION_FROM_ATTRIBUTE, regionFrom);
        session.setAttribute(USER_CITY_FROM_ATTRIBUTE, cityFrom);
        session.setAttribute(USER_COUNTRY_TO_ATTRIBUTE, countryTo);
        session.setAttribute(USER_REGION_TO_ATTRIBUTE, regionTo);
        session.setAttribute(USER_CITY_TO_ATTRIBUTE, cityTo);
        session.setAttribute(USER_DEPARTURE_DATE_ATTRIBUTE, departureDate);
        session.setAttribute(USER_DEPARTURE_TIME_ATTRIBUTE, departureTime);
        session.setAttribute(USER_COST_ATTRIBUTE, cost);
        session.setAttribute(USER_CURRENCY_ATTRIBUTE, currency);
        session.setAttribute(USER_PASSENGERS_ATTRIBUTE, passengers);
        session.setAttribute(USER_ADDITIONAL_INFORMATION_ATTRIBUTE, additionalInformation);
    }
}
