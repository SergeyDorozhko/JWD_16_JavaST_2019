package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.Address;
import by.dorozhko.poputka.entity.ErrorMessageConficurator;
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
import java.util.HashSet;
import java.util.Set;

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
    private static final String ERROR_COMMENTARY_ATTRIBUTE = "errorCommentary";

    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";
    private static final String FIELD_IS_EMPTY_ERROR_MESSAGE = "back.errors.fieldIsEmptyError";
    private static final String FIELD_FORMAT_ERROR_MESSAGE = "back.errors.fieldFormatError";

    public static final String INVALID_FORMAT_OF_START_COUNTRY = "invalid format of start country";
    public static final String INVALID_FORMAT_OF_START_REGION = "invalid format of start region";
    public static final String INVALID_FORMAT_OF_START_CITY = "invalid format of start city";
    public static final String INVALID_FORMAT_OF_DESTINATION_COUNTRY = "invalid format of destination country";
    public static final String INVALID_FORMAT_OF_DESTINATION_REGION = "invalid format of destination region";
    public static final String INVALID_FORMAT_OF_DESTINATION_CITY = "invalid format of destination city";
    public static final String INVALID_FORMAT_OF_DATE = "invalid format of date";
    public static final String INVALID_TIME_FORMAT = "invalid time format";
    public static final String INVALID_COST_VALUE = "invalid cost value";
    public static final String INVALID_FORMAT_OF_CURRENCY = "invalid format of currency";
    public static final String INVALID_NUMBER_OF_PASSENGERS = "invalid number of passengers";
    public static final String INVALID_CHARACTERS_IN_COMMENTARY = "invalid characters in commentary";


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

        resourceBundle = takeLocale(request);

        String button = request.getParameter(BUTTON);
        boolean isSaveActionAndValid = button != null
                && button.equals(ADD_JOURNEY_BUTTON) && checkData();

        if (isSaveActionAndValid) {
            JourneyService journeyService = ServiceFactory.getInstance()
                    .getJoureyService();
            User user = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
            User driverId = new User();
            driverId.setId(user.getId());
            Address startAddress
                    = new Address(countryFrom, regionFrom, cityFrom);
            Address destinationAddress
                    = new Address(countryTo, regionTo, cityTo);
            Journey journey = new Journey(driverId, startAddress,
                    destinationAddress, departureTime, departureDate,
                    Double.parseDouble(cost), currency,
                    Integer.parseInt(passengers), additionalInformation);
            String format = String.format("creating journey: %s", journey.getDriver().getId());
            logger.debug(format);
            try {
                journeyService.createNewJourney(journey);

                return request.getContextPath() + SUCCESSFUL_REDIRECT_URL;

            } catch (ExceptionService exceptionService) {
                logger.error(exceptionService);
                getErrorMessage(exceptionService.getMessage());
            }
        }

        setUserInputData();
        return request.getContextPath() + ERROR_REDIRECT_URL;
    }

    private void getAllAttributesData(final HttpServletRequest request) {
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

    private boolean checkData() {
        logger.debug("check data start");
        int countErrors = 0;

        countErrors += checkForIntegerValue(countryFrom, ERROR_COUNTRY_FROM_ATTRIBUTE);
        countErrors += checkForIntegerValue(regionFrom, ERROR_REGION_FROM_ATTRIBUTE);
        countErrors += checkForIntegerValue(cityFrom, ERROR_CITY_FROM_ATTRIBUTE);
        countErrors += checkForIntegerValue(countryTo, ERROR_COUNTRY_TO_ATTRIBUTE);
        countErrors += checkForIntegerValue(regionTo, ERROR_REGION_TO_ATTRIBUTE);
        countErrors += checkForIntegerValue(cityTo, ERROR_CITY_TO_ATTRIBUTE);
        countErrors += checkForDateValue(departureDate, ERROR_DEPARTURE_DATE_ATTRIBUTE);
        countErrors += checkForTimeValue(departureTime, ERROR_DEPARTURE_TIME_ATTRIBUTE);
        countErrors += checkForDoubleValue(cost, ERROR_COST_ATTRIBUTE);
        countErrors += checkForIntegerValue(currency, ERROR_CURRENCY_ATTRIBUTE);
        countErrors += checkForIntegerValue(passengers, ERROR_PASSENGERS_ATTRIBUTE);

        String format = String.format("find %d errors", countErrors);
        logger.debug(format);
        return countErrors == 0;
    }

    private int checkForIntegerValue(String data, String attribute) {
        int countErrors = 0;
        if (data == null || data.length() == 0) {
            session.setAttribute(attribute,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(data);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(attribute,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        return countErrors;
    }

    private int checkForDoubleValue(String data, String attribute) {
        int countErrors = 0;
        if (data == null || data.length() == 0) {
            session.setAttribute(attribute,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Double.parseDouble(data);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(attribute,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        return countErrors;
    }

    private int checkForDateValue(String data, String attribute) {
        int countErrors = 0;
        if (data == null || data.length() == 0) {
            session.setAttribute(attribute,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalDate.parse(data);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(attribute,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        return countErrors;
    }

    private int checkForTimeValue(String data, String attribute) {
        int countErrors = 0;
        if (data == null || data.length() == 0) {
            session.setAttribute(attribute,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalTime.parse(data);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(attribute,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        return countErrors;
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

    private void getErrorMessage(String msg) {
        Set<ErrorMessageConficurator> errorList = expectedMessages();

        boolean isFind = false;

        for (ErrorMessageConficurator errorMessageConficurator : errorList) {
            if (msg.contains(errorMessageConficurator.getExceptionMessage())) {
                session.setAttribute(errorMessageConficurator.getAttribute(),
                        resourceBundle.getString(errorMessageConficurator
                                .getMessageForUser()));
                isFind = true;
            }
        }
        if (!isFind) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
        }
    }

    private Set<ErrorMessageConficurator> expectedMessages() {

        Set<ErrorMessageConficurator> errorList = new HashSet<>();


        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_START_COUNTRY,
                ERROR_COUNTRY_FROM_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_START_REGION,
                ERROR_REGION_FROM_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_START_CITY,
                ERROR_CITY_FROM_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_DESTINATION_COUNTRY,
                ERROR_COUNTRY_TO_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_DESTINATION_REGION,
                ERROR_REGION_TO_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_DESTINATION_CITY,
                ERROR_CITY_TO_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_DATE,
                ERROR_DEPARTURE_DATE_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_TIME_FORMAT,
                ERROR_DEPARTURE_TIME_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_COST_VALUE,
                ERROR_COST_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_FORMAT_OF_CURRENCY,
                ERROR_CURRENCY_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_NUMBER_OF_PASSENGERS,
                ERROR_PASSENGERS_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_CHARACTERS_IN_COMMENTARY,
                ERROR_COMMENTARY_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));

        return errorList;
    }


}
