package by.dorozhko.poputka.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutAction extends AllUsersAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("here");
//            session.removeAttribute("authorizedUser");
            session.invalidate();
        }
        return new JourneyListForMainPage().execute(request, response);
    }
}
