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
import java.util.ResourceBundle;

public class DeleteProfileAction extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());


    private String password;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("start delete Account ok");

        HttpSession session = request.getSession(false);

        UserService userService
                = ServiceFactory.getInstance().getUserService();

        getAllAttributes(request);
        if (checkData()) {
            User actionUser = (User) session.getAttribute("authorizedUser");
            User user = new User();
            user.setId(actionUser.getId());
            user.setLogin(actionUser.getLogin());
            user.setPassword(password);
            logger.debug(String.format("user data collected: %s", user));
            try {
                logger.debug("start delete user");
                boolean result = userService.deleteUser(user);
                logger.debug("take answer from service");
                if (result) {
                    session.invalidate();
                    return request.getContextPath() + "/main.html";
                }

            } catch (ExceptionService exceptionService) {
                logger.error(String.format("sqlmsg : %s",
                        exceptionService.getMessage()));

            }
            ResourceBundle resourceBundle = takeLocale(request);
            session.setAttribute("unknownError", resourceBundle.getString("back.errors.incorrectPassword"));
        }

        return request.getContextPath() + "/viewUserProfile.html";

    }


    private void getAllAttributes(HttpServletRequest request) {
        password = request.getParameter("password");

        logger.debug("all params take ok");
    }

    private boolean checkData() {
        logger.debug("check data start");
        return password != null && password.length() != 0;
    }


}
