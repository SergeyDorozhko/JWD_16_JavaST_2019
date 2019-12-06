package by.dorozhko.poputka.controller.action.autorized_user;

import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class ViewJourney extends AuthorizedUser {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);

        String id = request.getParameter("journeyId");
        try {
            int journeyId = Integer.parseInt(id);
            JourneyService service = ServiceFactory.getInstance().getJoureyService();
            Journey journey = service.findJourney(journeyId);
            logger.debug(String.format("journeyIfo : %s", journey));
            request.setAttribute("journey", journey);
        } catch (NumberFormatException ex) {
            logger.error(ex);
            ResourceBundle resourceBundle = takeLocale(request);
            request.setAttribute("unknownError", resourceBundle.getString("back.errors.unknownError"));
        }

        return "/WEB-INF/jsp/viewJourney.jsp";
    }
}
