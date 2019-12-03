package by.dorozhko.poputka.controller.action.autorized_user;

import by.dorozhko.poputka.controller.action.autorized_user.user.UserAction;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewProfilePage extends AuthorizedUser {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        getCookieAction().setCookie(request, response);
        UserService service = ServiceFactory.getInstance().getUserService();
        User user = (User) session.getAttribute("authorizedUser");
        try {
            User userData = service.findById(user.getId());
            request.setAttribute("userData", userData);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
            //todo think about false action.
        }

        return "/WEB-INF/jsp/viewUserProfile.jsp";
    }
}
