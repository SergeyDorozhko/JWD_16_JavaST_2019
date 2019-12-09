package by.dorozhko.poputka.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutAction extends AllUsersAction {
    private static final String REDIRECT_URI = "/main.html";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return request.getContextPath() + REDIRECT_URI;
    }
}
