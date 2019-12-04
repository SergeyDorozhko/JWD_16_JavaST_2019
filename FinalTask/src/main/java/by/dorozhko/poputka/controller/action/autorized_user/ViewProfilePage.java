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
        setLocaleToCookie(request, response);
        UserService service = ServiceFactory.getInstance().getUserService();
        User actionUser = (User) session.getAttribute("authorizedUser");
        User user = new User();
        user.setId(actionUser.getId());
        user.setLogin(actionUser.getLogin());
        try {
            User userData = service.findById(user.getId());
            request.setAttribute("userData", userData);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
        }

        logger.debug("done without mistakes.");
        attributesData(request);
        return "/WEB-INF/jsp/viewUserProfile.jsp";
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());

        String unknownError =
                (String) session.getAttribute("unknownError");
        if (unknownError != null) {
            request.setAttribute("unknownError", unknownError);
            session.removeAttribute("unknownError");
        }

    }
}
