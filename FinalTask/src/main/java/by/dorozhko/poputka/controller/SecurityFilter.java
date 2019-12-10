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

            Action action = (Action) httpRequest.getAttribute("action");
            logger.debug(action.getClass().getSimpleName());
            Set<Role> allowRoles = action.getAllowRoles();
            Set<String> allowMethods = action.getAllowMethods();
            logger.debug(allowRoles);
            String userName = "unauthorized user";
            logger.debug("");
            HttpSession session = httpRequest.getSession(false);
            logger.debug("get session ok");
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
                if (user == null) {
                    user = new User();
                    user.setLogin(userName);
                    user.setRole(Role.GUEST.getId());
                    session.setAttribute("authorizedUser", user);

                }
                action.setUserOfAction(user);
                String errorMessage =
                        (String) session.getAttribute("SecurityMessage");
                if (errorMessage != null) {
                    httpRequest.setAttribute("message", errorMessage);
                    session.removeAttribute("SecurityMessage");
                }
            }
            logger.debug("set user to session ok");
            boolean canExecute = allowRoles == null;

            if (user != null) {
                canExecute = canExecute || allowRoles.contains(user.getRole()) && allowMethods.contains(httpRequest.getMethod());
                logger.debug(user.getRole());
            } else {
                canExecute = allowRoles.contains(Role.GUEST) && allowMethods.contains(httpRequest.getMethod());
            }

            if (canExecute) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                logger.info(String.format("Trying of %s access to forbidden resource", userName));
                if (session != null && !(action instanceof AllUsersAction)) {
                    session.setAttribute("SecurityMessage", "Доступ запрещён");
                }
                servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);

            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
    }

}
