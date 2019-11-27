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
            HttpServletRequest httpRequest
                    = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse
                    = (HttpServletResponse) servletResponse;
            Action action = (Action) httpRequest.getAttribute("action");
            logger.debug(action.getClass().getSimpleName());
            Set<Role> allowRoles = action.getAllowRoles();
            logger.debug(allowRoles);
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
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
                        (String) session.getAttribute("SecurityFilterMessage");
                if (errorMessage != null) {
                    httpRequest.setAttribute("message", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }
            boolean canExecute = allowRoles == null;
            if (user != null) {
                userName = "\"" + user.getLogin() + "\" user";
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }
            logger.debug(user.getRole());
            if (canExecute) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                logger.info(String.format("Trying of %s access to forbidden resource", userName));
                if (session != null && !(action instanceof AllUsersAction)) {
                    session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
                }
                servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);

//                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
    }

}
