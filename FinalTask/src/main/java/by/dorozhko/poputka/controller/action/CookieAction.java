package by.dorozhko.poputka.controller.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieAction {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    public void setCookie(HttpServletRequest request, HttpServletResponse response) {
        String value = request.getParameter("local");
        logger.debug(String.format("take local tag : %s ", value));
        if (value != null) {
            Cookie localCookie = new Cookie("lang", value);
            response.addCookie(localCookie);
            request.setAttribute("lang", value);
        }

    }
}
