package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteCarAction extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String REDIRECT_URL = "/viewUserProfile.html";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);

        UserService service = ServiceFactory.getInstance().getUserService();

        try {
            service.deleteCar(user);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
        }

        return request.getContextPath() + REDIRECT_URL;
    }
}
