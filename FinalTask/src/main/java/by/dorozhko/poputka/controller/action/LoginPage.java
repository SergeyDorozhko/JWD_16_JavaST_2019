package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPage extends AllUsersAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return "/WEB-INF/jsp/login.jsp";
    }
}
