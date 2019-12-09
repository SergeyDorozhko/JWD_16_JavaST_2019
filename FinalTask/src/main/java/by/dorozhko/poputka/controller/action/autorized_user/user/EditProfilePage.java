package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EditProfilePage extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/editProfile.jsp";


    private static final String MAP_OF_GENDORS = "gendersMap";
    private static final String MAP_OF_COUNTRIES = "countriesMap";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";
    private static final String USER_DATA_ATTRIBUTE = "userData";


    private static final String ERROR_LOGIN_ATTRIBUTE = "errorLogin";
    private static final String ERROR_FIRST_NAME_ATTRIBUTE = "errorFirstName";
    private static final String ERROR_LAST_NAME_ATTRIBUTE = "errorLastName";
    private static final String ERROR_EMAIL_ATTRIBUTE = "errorEmail";
    private static final String ERROR_BIRTHDAY_ATTRIBUTE = "errorBirthday";
    private static final String ERROR_PHONE_ATTRIBUTE = "errorPhoneNumber";
    private static final String ERROR_COUNTRY_ATTRIBUTE = "errorCountry";
    private static final String ERROR_PASSPORT_NUMBER_ATTRIBUTE = "errorPassportNumber";
    private static final String ERROR_PASSPORT_DATE_ATTRIBUTE = "errorPassportDate";
    private static final String ERROR_SEX_ATTRIBUTE = "errorSex";
    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private Set<String> listOfAattributes;

    public EditProfilePage() {
        listOfAattributes = new HashSet<>();
        listOfAattributes.add(ERROR_LOGIN_ATTRIBUTE);
        listOfAattributes.add(ERROR_FIRST_NAME_ATTRIBUTE);
        listOfAattributes.add(ERROR_LAST_NAME_ATTRIBUTE);
        listOfAattributes.add(ERROR_EMAIL_ATTRIBUTE);
        listOfAattributes.add(ERROR_BIRTHDAY_ATTRIBUTE);
        listOfAattributes.add(ERROR_PHONE_ATTRIBUTE);
        listOfAattributes.add(ERROR_COUNTRY_ATTRIBUTE);
        listOfAattributes.add(ERROR_PASSPORT_NUMBER_ATTRIBUTE);
        listOfAattributes.add(ERROR_PASSPORT_DATE_ATTRIBUTE);
        listOfAattributes.add(ERROR_SEX_ATTRIBUTE);
        listOfAattributes.add(UNKNOWN_ERROR_ATTRIBUTE);

    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start edit profile page");
        HttpSession session = request.getSession(false);
        setLocaleToCookie(request, response);
        attributesData(request);

        UserService service = ServiceFactory.getInstance().getUserService();
        User actionUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        User user = new User();
        user.setId(actionUser.getId());
        user.setLogin(actionUser.getLogin());
        Map<Integer, String> gendors = ServiceFactory.getInstance().getCatalogService().getGenders();
        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();

        request.setAttribute(MAP_OF_GENDORS, gendors);
        request.setAttribute(MAP_OF_COUNTRIES, countries);
        try {
            User userData = service.takeDataForEditProfile(user.getId());
            request.setAttribute(USER_DATA_ATTRIBUTE, userData);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
        }

        return FORWARD_PAGE;
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());

        for (String attribute : listOfAattributes) {
            String attributeData =
                    (String) session.getAttribute(attribute);
            if (attributeData != null) {
                request.setAttribute(attribute, attributeData);
                session.removeAttribute(attribute);
            }
        }
    }
}
