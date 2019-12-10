package by.dorozhko.poputka.controller.action;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginPage extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/login.jsp";

    private static final String ERROR_LOGIN_ATTRIBUTE = "errorLogin";
    private static final String ERROR_PASSWORD_ATTRIBUTE = "errorPassword";
    private static final String USER_LOGIN_ATTRIBUTE = "userLogin";

    private static final String GET_METHOD = "GET";


    private Set<String> attributesList;

    public LoginPage() {
        setAllowMethods(GET_METHOD);
        attributesList = new HashSet<>();
        attributesList.add(ERROR_LOGIN_ATTRIBUTE);
        attributesList.add(ERROR_PASSWORD_ATTRIBUTE);
        attributesList.add(USER_LOGIN_ATTRIBUTE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);
        attributesData(request);
        return FORWARD_PAGE;
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());

        for (String attribute : attributesList) {
            String attributeData =
                    (String) session.getAttribute(attribute);
            if (attributeData != null) {
                request.setAttribute(attribute, attributeData);
                session.removeAttribute(attribute);
            }
        }

    }
}
