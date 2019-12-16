package by.dorozhko.poputka.controller;

import by.dorozhko.poputka.controller.action.AllUsersAction;
import by.dorozhko.poputka.entity.Role;
import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {

    public static final String ACTION = "action";
    public static final String AUTHORIZED_USER = "authorizedUser";
    public static final String SECURITY_MESSAGE = "error";
    public static final String MESSAGE = "message";
    public static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    public static final String ACCESS_ERROR = "securityMessage";

    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest
                && servletResponse instanceof HttpServletResponse) {
            logger.debug("start security filter");
            HttpServletRequest httpRequest
                    = (HttpServletRequest) servletRequest;

            logger.debug(String.format("get method : %s", httpRequest.getMethod()));

            Action action = (Action) httpRequest.getAttribute(ACTION);
            logger.debug(action.getClass().getSimpleName());
            Set<Role> allowRoles = action.getAllowRoles();
            Set<String> allowMethods = action.getAllowMethods();
            logger.debug(allowRoles);
            String userName = "unauthorized user";
            logger.debug("");
            HttpSession session = httpRequest.getSession();
            logger.debug("get session ok");
            User user = (User) session.getAttribute(AUTHORIZED_USER);
            if (user == null) {
                user = new User();
                user.setLogin(userName);
                user.setRole(Role.GUEST.getId());
                session.setAttribute(AUTHORIZED_USER, user);
            }
            action.setUserOfAction(user);
            String errorMessage =
                    (String) session.getAttribute(SECURITY_MESSAGE);
            if (errorMessage != null) {
                httpRequest.setAttribute(MESSAGE, errorMessage);
                session.removeAttribute(SECURITY_MESSAGE);
            }
            logger.debug("set user to session ok");
            boolean canExecute = allowRoles == null;
            canExecute = canExecute || allowRoles.contains(user.getRole()) && allowMethods.contains(httpRequest.getMethod());
            logger.debug(user.getRole());
            if (canExecute) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                String format = String.format("Trying of %s access to forbidden resource", userName);
                logger.info(format);
                if (!(action instanceof AllUsersAction)) {
                    httpRequest.setAttribute(SECURITY_MESSAGE, ACCESS_ERROR);
                }
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            servletRequest.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(servletRequest, servletResponse);
        }
    }
}
