package by.dorozhko.poputka.controller.action.autorized_user;

import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewJourney extends AuthorizedUser {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/viewJourney.jsp";

    private static final String ERROR_ATTRIBUTE = "error";
    private static final String ERROR_ID_MESSAGE = "invalidIdValue";
    private static final String JOURNEY_ID_ATTRIBUTE = "journeyId";
    private static final String JOURNEY_ATTRIBUTE = "journey";

    public ViewJourney() {
        setAllowMethods(GET_METHOD);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);

        String id = request.getParameter(JOURNEY_ID_ATTRIBUTE);
        logger.debug(id);
        try {
            int journeyId = Integer.parseInt(id);
            JourneyService service = ServiceFactory.getInstance().getJoureyService();
            Journey journey = service.findJourney(journeyId);
            logger.debug(String.format("journeyIfo : %s", journey));

            request.setAttribute(JOURNEY_ATTRIBUTE, journey);
        } catch (NumberFormatException ex) {
            logger.error(ex);
            resourceBundle = takeLocale(request);
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_ID_MESSAGE);
            try {

                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e) {
                logger.error(e);
            }
        }

        return FORWARD_PAGE;
    }
}
