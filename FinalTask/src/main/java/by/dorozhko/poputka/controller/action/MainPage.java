package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPage extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        JourneyService journeyService
                = ServiceFactory.getInstance().getJoureyService();
        setLocaleToCookie(request, response);
        logger.debug(String.format("local: %s", request.getParameter("local")));

        request.setAttribute("journeyList",
                journeyService.findAllJourneyShort());
        return "/WEB-INF/jsp/main.jsp";
    }
}
