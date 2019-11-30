package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationPage extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        attributesData(request);
        Map<Integer, String> gendors = ServiceFactory.getInstance().getCatalogService().getGenders();
        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();

        request.setAttribute("gendersMap", gendors);
        request.setAttribute("countriesMap", countries);

        return "/WEB-INF/jsp/registration.jsp";
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());
        String errorLogin =
                (String) session.getAttribute("errorLogin");
        if (errorLogin != null) {
            request.setAttribute("errorLogin", errorLogin);
            session.removeAttribute("errorLogin");
        }
        String errorFirstName =
                (String) session.getAttribute("errorFirstName");
        if (errorFirstName != null) {
            request.setAttribute("errorFirstName", errorFirstName);
            session.removeAttribute("errorFirstName");
        }

        String errorLastName =
                (String) session.getAttribute("errorLastName");
        if (errorLastName != null) {
            request.setAttribute("errorLastName", errorLastName);
            session.removeAttribute("errorLastName");
        }
        String errorEmail =
                (String) session.getAttribute("errorEmail");
        if (errorEmail != null) {
            request.setAttribute("errorEmail", errorEmail);
            session.removeAttribute("errorEmail");
        }
        String errorPassword =
                (String) session.getAttribute("errorPassword");
        if (errorPassword != null) {
            request.setAttribute("errorPassword", errorPassword);
            session.removeAttribute("errorPassword");
        }

        String errorBirthday =
                (String) session.getAttribute("errorBirthday");
        if (errorBirthday != null) {
            request.setAttribute("errorBirthday", errorBirthday);
            session.removeAttribute("errorBirthday");
        }

        String errorPhoneNumber =
                (String) session.getAttribute("errorPhoneNumber");
        if (errorPhoneNumber != null) {
            request.setAttribute("errorPhoneNumber", errorPhoneNumber);
            session.removeAttribute("errorPhoneNumber");
        }

        String errorCountry =
                (String) session.getAttribute("errorCountry");
        if (errorCountry != null) {
            request.setAttribute("errorCountry", errorCountry);
            session.removeAttribute("errorCountry");
        }
        String errorPassportNumber =
                (String) session.getAttribute("errorPassportNumber");
        if (errorPassportNumber != null) {
            request.setAttribute("errorPassportNumber", errorPassportNumber);
            session.removeAttribute("errorPassportNumber");
        }
        String errorPassportDate =
                (String) session.getAttribute("errorPassportDate");
        if (errorPassportDate != null) {
            request.setAttribute("errorPassportDate", errorPassportDate);
            session.removeAttribute("errorPassportDate");
        }
        String errorSex =
                (String) session.getAttribute("errorSex");
        if (errorSex != null) {
            request.setAttribute("errorSex", errorSex);
            session.removeAttribute("errorSex");
        }
        String unknownError =
                (String) session.getAttribute("unknownError");
        if (unknownError != null) {
            request.setAttribute("unknownError", unknownError);
            session.removeAttribute("unknownError");
        }
        String userLogin =
                (String) session.getAttribute("userLogin");
        logger.debug(String.format("User login was: %s", userLogin));
        if (userLogin != null) {
            request.setAttribute("userLogin", userLogin);
            session.removeAttribute("userLogin");
        }
        String userFirstName =
                (String) session.getAttribute("userFirstName");
        if (userFirstName != null) {
            request.setAttribute("userFirstName", userFirstName);
            session.removeAttribute("userFirstName");
        }
        String userLastName =
                (String) session.getAttribute("userLastName");
        if (userLastName != null) {
            request.setAttribute("userLastName", userLastName);
            session.removeAttribute("userLastName");
        }
        String userEmail =
                (String) session.getAttribute("userEmail");
        if (userEmail != null) {
            request.setAttribute("userEmail", userEmail);
            session.removeAttribute("userEmail");
        }
        String userBirthday =
                (String) session.getAttribute("userBirthday");
        if (userEmail != null) {
            request.setAttribute("userBirthday", userBirthday);
            session.removeAttribute("userBirthday");
        }
        String userPhoneNumber =
                (String) session.getAttribute("userPhoneNumber");
        if (userPhoneNumber != null) {
            request.setAttribute("userPhoneNumber", userPhoneNumber);
            session.removeAttribute("userPhoneNumber");
        }
        String userCountry =
                (String) session.getAttribute("userCountry");
        if (userCountry != null) {
            request.setAttribute("userCountry", userCountry);
            session.removeAttribute("userCountry");
        }
        String userPassportNumber =
                (String) session.getAttribute("userPassportNumber");
        if (userPassportNumber != null) {
            request.setAttribute("userPassportNumber", userPassportNumber);
            session.removeAttribute("userPassportNumber");
        }
        String userPassportDate =
                (String) session.getAttribute("userPassportDate");
        if (userPassportDate != null) {
            request.setAttribute("userPassportDate", userPassportDate);
            session.removeAttribute("userPassportDate");
        }
        String userSex = (String) session.getAttribute("userSex");
        if (userSex != null) {
            request.setAttribute("userSex", userSex);
            session.removeAttribute("userSex");
        }
    }
}
