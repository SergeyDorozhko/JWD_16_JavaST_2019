package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.ResourceBundle;

public class AddTripPage extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private String userCountryFrom;
    private String userCountryTo;
    private String userRegionFrom;
    private String userRegionTo;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle resourceBundle = takeLocale(request);

        setLocaleToCookie(request, response);
        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();
        request.setAttribute("countriesMap", countries);
        Map<Integer, String> currencies = ServiceFactory.getInstance().getCatalogService().getCurrencies();
        request.setAttribute("currenciesMap", currencies);
        attributesData(request);
        if (userCountryFrom != null && userCountryFrom.length() != 0) {
            try {
                Map<Integer, String> regions = ServiceFactory.getInstance().getCatalogService().getRegionsOfCountry(Integer.parseInt(userCountryFrom));
                request.setAttribute("regionFromMap", regions);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute("errorCountryFrom", resourceBundle.getString("back.errors.fieldFormatError"));
            }
        }
        if (userCountryTo != null && userCountryTo.length() != 0) {
            try {
                Map<Integer, String> regions = ServiceFactory.getInstance().getCatalogService().getRegionsOfCountry(Integer.parseInt(userCountryTo));
                request.setAttribute("regionToMap", regions);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute("errorCountryTo", resourceBundle.getString("back.errors.fieldFormatError"));
            }
        }

        if (userRegionFrom != null && userRegionFrom.length() != 0) {
            try {
                Map<Integer, String> cities = ServiceFactory.getInstance().getCatalogService().getCitiesOfRegion(Integer.parseInt(userRegionFrom));
                request.setAttribute("citiesFromMap", cities);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute("userRegionFrom", resourceBundle.getString("back.errors.fieldFormatError"));
            }
        }
        if (userRegionTo != null && userRegionTo.length() != 0) {
            try {
                Map<Integer, String> cities = ServiceFactory.getInstance().getCatalogService().getCitiesOfRegion(Integer.parseInt(userRegionTo));
                request.setAttribute("citiesToMap", cities);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                request.setAttribute("userRegionTo", resourceBundle.getString("back.errors.fieldFormatError"));
            }
        }

        return "/WEB-INF/jsp/addTrip.jsp";
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);


        userCountryFrom =
                (String) session.getAttribute("userCountryFrom");
        if (userCountryFrom != null) {
            request.setAttribute("userCountryFrom", userCountryFrom);
            session.removeAttribute("userCountryFrom");
        }

        userRegionFrom =
                (String) session.getAttribute("userRegionFrom");
        if (userRegionFrom != null) {
            request.setAttribute("userRegionFrom", userRegionFrom);
            session.removeAttribute("userRegionFrom");
        }

        String userCityFrom =
                (String) session.getAttribute("userCityFrom");
        if (userCityFrom != null) {
            request.setAttribute("userCityFrom", userCityFrom);
            session.removeAttribute("userCityFrom");
        }

        userCountryTo =
                (String) session.getAttribute("userCountryTo");
        if (userCountryTo != null) {
            request.setAttribute("userCountryTo", userCountryTo);
            session.removeAttribute("userCountryTo");
        }

        userRegionTo =
                (String) session.getAttribute("userRegionTo");
        if (userRegionTo != null) {
            request.setAttribute("userRegionTo", userRegionTo);
            session.removeAttribute("userRegionTo");
        }

        String userCityTo =
                (String) session.getAttribute("userCityTo");
        if (userCityTo != null) {
            request.setAttribute("userCityTo", userCityTo);
            session.removeAttribute("userCityTo");
        }

        String userDepartureDate =
                (String) session.getAttribute("userDepartureDate");
        if (userDepartureDate != null) {
            request.setAttribute("userDepartureDate", userDepartureDate);
            session.removeAttribute("userDepartureDate");
        }

        String userDepartureTime =
                (String) session.getAttribute("userDepartureTime");
        if (userDepartureTime != null) {
            request.setAttribute("userDepartureTime", userDepartureTime);
            session.removeAttribute("userDepartureTime");
        }

        String userCost =
                (String) session.getAttribute("userCost");
        if (userCost != null) {
            request.setAttribute("userCost", userCost);
            session.removeAttribute("userCost");
        }

        String userCurrency =
                (String) session.getAttribute("userCurrency");
        if (userCurrency != null) {
            request.setAttribute("userCurrency", userCurrency);
            session.removeAttribute("userCurrency");
        }

        String userPassengers =
                (String) session.getAttribute("userPassengers");
        if (userPassengers != null) {
            request.setAttribute("userPassengers", userPassengers);
            session.removeAttribute("userPassengers");
        }

        String userAdditionalInformation =
                (String) session.getAttribute("userAdditionalInformation");
        if (userAdditionalInformation != null) {
            request.setAttribute("userAdditionalInformation", userAdditionalInformation);
            session.removeAttribute("userAdditionalInformation");
        }

        String unknownError =
                (String) session.getAttribute("unknownError");
        if (unknownError != null) {
            request.setAttribute("unknownError", unknownError);
            session.removeAttribute("unknownError");
        }

        String errorCountryFrom =
                (String) session.getAttribute("errorCountryFrom");
        if (errorCountryFrom != null) {
            request.setAttribute("errorCountryFrom", errorCountryFrom);
            session.removeAttribute("errorCountryFrom");
        }
        String errorRegionFrom =
                (String) session.getAttribute("errorRegionFrom");
        if (errorRegionFrom != null) {
            request.setAttribute("errorRegionFrom", errorRegionFrom);
            session.removeAttribute("errorRegionFrom");
        }
        String errorCityFrom =
                (String) session.getAttribute("errorCityFrom");
        if (errorCityFrom != null) {
            request.setAttribute("errorCityFrom", errorCityFrom);
            session.removeAttribute("errorCityFrom");
        }
        String errorCountryTo =
                (String) session.getAttribute("errorCountryTo");
        if (errorCountryTo != null) {
            request.setAttribute("errorCountryTo", errorCountryTo);
            session.removeAttribute("errorCountryTo");
        }
        String errorRegionTo =
                (String) session.getAttribute("errorRegionTo");
        if (errorRegionTo != null) {
            request.setAttribute("errorRegionTo", errorRegionTo);
            session.removeAttribute("errorRegionTo");
        }
        String errorCityTo =
                (String) session.getAttribute("errorCityTo");
        if (errorCityTo != null) {
            request.setAttribute("errorCityTo", errorCityTo);
            session.removeAttribute("errorCityTo");
        }
        String errorDepartureDate =
                (String) session.getAttribute("errorDepartureDate");
        if (errorDepartureDate != null) {
            request.setAttribute("errorDepartureDate", errorDepartureDate);
            session.removeAttribute("errorDepartureDate");
        }
        String errorDepartureTime =
                (String) session.getAttribute("errorDepartureTime");
        if (errorDepartureTime != null) {
            request.setAttribute("errorDepartureTime", errorDepartureTime);
            session.removeAttribute("errorDepartureTime");
        }
        String errorCost =
                (String) session.getAttribute("errorCost");
        if (errorCost != null) {
            request.setAttribute("errorCost", errorCost);
            session.removeAttribute("errorCost");
        }
        String errorCurrency =
                (String) session.getAttribute("errorCurrency");
        if (errorCurrency != null) {
            request.setAttribute("errorCurrency", errorCurrency);
            session.removeAttribute("errorCurrency");
        }
        String errorPassengers =
                (String) session.getAttribute("errorPassengers");
        if (unknownError != null) {
            request.setAttribute("errorPassengers", errorPassengers);
            session.removeAttribute("errorPassengers");
        }

    }
}