package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.dao.FactoryDao;
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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("authorizedUser");

        UserService service = ServiceFactory.getInstance().getUserService();

        try {
            service.deteleCar(user);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
        }

        return request.getContextPath() + "/viewUserProfile.html";
    }
}
