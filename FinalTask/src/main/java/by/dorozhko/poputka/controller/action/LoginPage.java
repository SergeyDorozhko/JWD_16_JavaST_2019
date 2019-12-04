package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPage extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);
        attributesData(request);
        return "/WEB-INF/jsp/login.jsp";
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
        String errorPassword =
                (String) session.getAttribute("errorPassword");
        if (errorPassword != null) {
            request.setAttribute("errorPassword", errorPassword);
            session.removeAttribute("errorPassword");
        }
        String userLogin =
                (String) session.getAttribute("userLogin");
        logger.debug(String.format("User login was: %s", userLogin));
        if (userLogin != null) {
            request.setAttribute("userLogin", userLogin);
            session.removeAttribute("userLogin");
        }
    }
}
