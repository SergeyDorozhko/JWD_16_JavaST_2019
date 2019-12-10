package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationPage extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());


    private static final String MAP_OF_GENDORS = "gendersMap";
    private static final String MAP_OF_COUNTRIES = "countriesMap";

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/registration.jsp";


    private static final String ERROR_LOGIN_ATTRIBUTE = "errorLogin";
    private static final String ERROR_FIRST_NAME_ATTRIBUTE = "errorFirstName";
    private static final String ERROR_LAST_NAME_ATTRIBUTE = "errorLastName";
    private static final String ERROR_EMAIL_ATTRIBUTE = "errorEmail";
    private static final String ERROR_PASSWORD_ATTRIBUTE = "errorPassword";
    private static final String ERROR_BIRTHDAY_ATTRIBUTE = "errorBirthday";
    private static final String ERROR_PHONE_ATTRIBUTE = "errorPhoneNumber";
    private static final String ERROR_COUNTRY_ATTRIBUTE = "errorCountry";
    private static final String ERROR_PASSPORT_NUMBER_ATTRIBUTE = "errorPassportNumber";
    private static final String ERROR_PASSPORT_DATE_ATTRIBUTE = "errorPassportDate";
    private static final String ERROR_SEX_ATTRIBUTE = "errorSex";
    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private static final String USER_LOGIN_ATTRIBUTE = "userLogin";
    private static final String USER_FIRST_NAME_ATTRIBUTE = "userFirstName";
    private static final String USER_LAST_NAME_ATTRIBUTE = "userLastName";
    private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    private static final String USER_BIRTHDAY_ATTRIBUTE = "userBirthday";
    private static final String USER_PHONE_ATTRIBUTE = "userPhoneNumber";
    private static final String USER_COUNTRY_ATTRIBUTE = "userCountry";
    private static final String USER_PASSPORT_NUMBER_ATTRIBUTE = "userPassportNumber";
    private static final String USER_PASSPORT_DATE_ATTRIBUTE = "userPassportDate";
    private static final String USER_SEX_ATTRIBUTE = "userSex";

    private Set<String> listOfAttributes;

    public RegistrationPage() {
        setAllowMethods(GET_METHOD);
        listOfAttributes = new HashSet<>();
        listOfAttributes.add(ERROR_LOGIN_ATTRIBUTE);
        listOfAttributes.add(ERROR_FIRST_NAME_ATTRIBUTE);
        listOfAttributes.add(ERROR_LAST_NAME_ATTRIBUTE);
        listOfAttributes.add(ERROR_EMAIL_ATTRIBUTE);
        listOfAttributes.add(ERROR_PASSWORD_ATTRIBUTE);
        listOfAttributes.add(ERROR_BIRTHDAY_ATTRIBUTE);
        listOfAttributes.add(ERROR_PHONE_ATTRIBUTE);
        listOfAttributes.add(ERROR_COUNTRY_ATTRIBUTE);
        listOfAttributes.add(ERROR_PASSPORT_NUMBER_ATTRIBUTE);
        listOfAttributes.add(ERROR_PASSPORT_DATE_ATTRIBUTE);
        listOfAttributes.add(ERROR_SEX_ATTRIBUTE);
        listOfAttributes.add(UNKNOWN_ERROR_ATTRIBUTE);
        listOfAttributes.add(USER_LOGIN_ATTRIBUTE);
        listOfAttributes.add(USER_FIRST_NAME_ATTRIBUTE);
        listOfAttributes.add(USER_LAST_NAME_ATTRIBUTE);
        listOfAttributes.add(USER_EMAIL_ATTRIBUTE);
        listOfAttributes.add(USER_BIRTHDAY_ATTRIBUTE);
        listOfAttributes.add(USER_PHONE_ATTRIBUTE);
        listOfAttributes.add(USER_COUNTRY_ATTRIBUTE);
        listOfAttributes.add(USER_PASSPORT_NUMBER_ATTRIBUTE);
        listOfAttributes.add(USER_PASSPORT_DATE_ATTRIBUTE);
        listOfAttributes.add(USER_SEX_ATTRIBUTE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);

        attributesData(request);
        Map<Integer, String> gendors = ServiceFactory.getInstance().getCatalogService().getGenders();
        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();

        request.setAttribute(MAP_OF_GENDORS, gendors);
        request.setAttribute(MAP_OF_COUNTRIES, countries);

        return FORWARD_PAGE;
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());

        for (String attribut : listOfAttributes) {
            String setUpAttribute =
                    (String) session.getAttribute(attribut);
            if (setUpAttribute != null) {
                request.setAttribute(attribut, setUpAttribute);
                session.removeAttribute(attribut);
            }
        }
    }
}
