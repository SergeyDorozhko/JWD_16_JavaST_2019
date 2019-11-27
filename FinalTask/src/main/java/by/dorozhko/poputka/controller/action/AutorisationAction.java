package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutorisationAction extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        UserService userService
                = ServiceFactory.getInstance().getUserService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        logger.debug(String.format("Login : %s , password: %s", login, password));
        User user = null;
        try {
            user = userService.singIn(login, password);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }

        if (user != null) {
            session.setAttribute("authorizedUser",
                    user);
           return new JourneyListForMainPage().execute(request, response);
        }

        request.setAttribute("errorLoginPassMessage", "Неверный логин или пароль");

        return "/WEB-INF/jsp/login.jsp";
    }
}
