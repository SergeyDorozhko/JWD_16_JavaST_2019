package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.dao.FactoryDao;
import by.dorozhko.poputka.services.DataFromCatalogService;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
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

        errorMessage(request, response);
        Map<Integer, String> gendors = ServiceFactory.getInstance().getCatalogService().getGendors();

        request.setAttribute("gendersMap", gendors);
        return "/WEB-INF/jsp/registration.jsp";
    }

    private void errorMessage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        String passwordNotEqual =
                (String) session.getAttribute("passwordNotEqual");
        if (passwordNotEqual != null) {
            request.setAttribute("passwordNotEqual", passwordNotEqual);
            session.removeAttribute("passwordNotEqual");
        }
        String duplicateLogin =
                (String) session.getAttribute("duplicateLogin");
        if (duplicateLogin != null) {
            request.setAttribute("duplicateLogin", duplicateLogin);
            session.removeAttribute("duplicateLogin");
        }
        String duplicatePassport =
                (String) session.getAttribute("duplicatePassport");
        if (duplicatePassport != null) {
            request.setAttribute("duplicatePassport", duplicatePassport);
            session.removeAttribute("duplicatePassport");
        }

        String duplicatePhone =
                (String) session.getAttribute("duplicatePhone");
        if (duplicatePhone != null) {
            request.setAttribute("duplicatePhone", duplicatePhone);
            session.removeAttribute("duplicatePhone");
        }

        String duplicateEmail =
                (String) session.getAttribute("duplicateEmail");
        if (duplicateEmail != null) {
            request.setAttribute("duplicateEmail", duplicateEmail);
            session.removeAttribute("duplicateEmail");
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

        String userSex =  (String) session.getAttribute("userSex");
        if (userSex != null) {
            request.setAttribute("userSex", userSex);
            session.removeAttribute("userSex");
        }
    }
}
