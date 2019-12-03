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
import java.util.Map;

public class EditProfilePage extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start edit profile page");
        HttpSession session = request.getSession(false);
        getCookieAction().setCookie(request, response);
        attributesData(request);

        UserService service = ServiceFactory.getInstance().getUserService();
        User user = (User) session.getAttribute("authorizedUser");

        Map<Integer, String> gendors = ServiceFactory.getInstance().getCatalogService().getGenders();
        Map<Integer, String> countries = ServiceFactory.getInstance().getCatalogService().getCountries();

        request.setAttribute("gendersMap", gendors);
        request.setAttribute("countriesMap", countries);
        try {
            User userData = service.takeDataForEditProfile(user.getId());
            request.setAttribute("userData", userData);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
        }

        return "/WEB-INF/jsp/editProfile.jsp";
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

    }
}
