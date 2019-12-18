package by.dorozhko.poputka.controller.action.autorized_user;

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

public class ViewProfilePage extends AuthorizedUser {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/viewUserProfile.jsp";

    private static final String ERROR_FORWARD_PAGE = "/WEB-INF/jsp/error.jsp";

    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String USER_DATA_ATTRIBUTE = "userData";

    private static final String INVALID_ID_VALUE_MESSAGE = "invalid id";

    private static final String ERROR_ID_MESSAGE_TO_USER = "back.errors.invalidIdValue";

    public ViewProfilePage() {
        setAllowMethods(GET_METHOD);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        setLocaleToCookie(request, response);
        UserService service = ServiceFactory.getInstance().getUserService();
        User actionUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        User user = new User();
        user.setId(actionUser.getId());
        user.setLogin(actionUser.getLogin());
        try {
            User userData = service.findById(user.getId());
            request.setAttribute(USER_DATA_ATTRIBUTE, userData);
        } catch (ExceptionService exceptionService) {
            logger.error(exceptionService);
            getErrorMessage(exceptionService.getMessage(), request);
            return ERROR_FORWARD_PAGE;
        }

        logger.debug("done without mistakes.");
        attributesData(request);
        return FORWARD_PAGE;
    }

    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("set attributes data start");

        String unknownError =
                (String) session.getAttribute(UNKNOWN_ERROR_ATTRIBUTE);
        if (unknownError != null) {
            request.setAttribute(UNKNOWN_ERROR_ATTRIBUTE, unknownError);
            session.removeAttribute(UNKNOWN_ERROR_ATTRIBUTE);
        }

    }

    private void getErrorMessage(String msg, HttpServletRequest request) {

        ResourceBundle resourceBundle = takeLocale(request);

        if (msg.equals(INVALID_ID_VALUE_MESSAGE)) {
            request.setAttribute(UNKNOWN_ERROR_ATTRIBUTE, resourceBundle
                    .getString(ERROR_ID_MESSAGE_TO_USER));
        }
    }
}
