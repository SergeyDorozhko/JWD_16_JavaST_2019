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

        String button = request.getParameter("button");
        if (button != null && button.equals("add")) {
            if (checkData(resourceBundle)) {
                JourneyService journeyService = ServiceFactory.getInstance().getJoureyService();
                User user = (User) session.getAttribute("authorizedUser");
                User driverId = new User();
                driverId.setId(user.getId());
                Address startAddress = new Address(countryFrom, regionFrom, cityFrom);
                Address destinationAddress = new Address(countryTo, regionTo, cityTo);
                Journey journey = new Journey(driverId, startAddress, destinationAddress, departureTime, departureDate, Double.parseDouble(cost), currency, Integer.parseInt(passengers), additionalInformation);
                logger.debug(String.format("creating journey: %s", journey.getDriver().getId()));
                try {
                    journeyService.createNewJourney(journey);

                    return request.getContextPath() + "/listOfJourneys.html";

                } catch (ExceptionService exceptionService) {
                    logger.error(exceptionService);
                    session.setAttribute("unknownError", resourceBundle.getString("back.errors.unknownError"));
                }
            }
        }
        setUserInputData();
        return request.getContextPath() + "/addTrip.html";
    }

    private void getAllAttributesData(HttpServletRequest request) {
        countryFrom = request.getParameter("countryFrom");
        regionFrom = request.getParameter("regionFrom");
        cityFrom = request.getParameter("cityFrom");
        countryTo = request.getParameter("countryTo");
        regionTo = request.getParameter("regionTo");
        cityTo = request.getParameter("cityTo");
        departureDate = request.getParameter("departureDate");
        departureTime = request.getParameter("departureTime");
        cost = request.getParameter("cost");
        currency = request.getParameter("currency");
        passengers = request.getParameter("passengers");
        additionalInformation = request.getParameter("additionalInformation");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");
        int countErrors = 0;

        if (countryFrom.length() == 0) {
            session.setAttribute("errorCountryFrom", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(countryFrom);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorCountryFrom", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }
        }
        if (regionFrom.length() == 0) {
            session.setAttribute("errorRegionFrom", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(regionFrom);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorRegionFrom", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (cityFrom.length() == 0) {
            session.setAttribute("errorCityFrom", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(cityFrom);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorCityFrom", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (countryTo.length() == 0) {
            session.setAttribute("errorCountryTo", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(countryTo);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorCountryTo", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (regionTo.length() == 0) {
            session.setAttribute("errorRegionTo", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(regionTo);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorRegionTo", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (cityTo.length() == 0) {
            session.setAttribute("errorCityTo", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(cityTo);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorCityTo", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (departureDate.length() == 0) {
            session.setAttribute("errorDepartureDate", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                LocalDate.parse(departureDate);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute("errorDepartureDate", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (departureTime.length() == 0) {
            session.setAttribute("errorDepartureTime", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                LocalTime.parse(departureTime);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute("errorDepartureTime", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (cost.length() == 0) {
            session.setAttribute("errorCost", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Double.parseDouble(cost);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorCost", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (currency.length() == 0) {
            session.setAttribute("errorCurrency", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(currency);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorCurrency", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }
        if (passengers.length() == 0) {
            session.setAttribute("errorPassengers", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            try {
                Integer.parseInt(passengers);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute("errorPassengers", resourceBundle.getString("back.errors.fieldFormatError"));
                countErrors++;
            }

        }

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void setUserInputData() {
        session.setAttribute("userCountryFrom", countryFrom);
        session.setAttribute("userRegionFrom", regionFrom);
        session.setAttribute("userCityFrom", cityFrom);
        session.setAttribute("userCountryTo", countryTo);
        session.setAttribute("userRegionTo", regionTo);
        session.setAttribute("userCityTo", cityTo);
        session.setAttribute("userDepartureDate", departureDate);
        session.setAttribute("userDepartureTime", departureTime);
        session.setAttribute("userCost", cost);
        session.setAttribute("userCurrency", currency);
        session.setAttribute("userPassengers", passengers);
        session.setAttribute("userAdditionalInformation", additionalInformation);
    }
}
