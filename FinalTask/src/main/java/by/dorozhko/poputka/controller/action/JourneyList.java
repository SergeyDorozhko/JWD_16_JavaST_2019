package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JourneyList extends AllUsersAction {

    private static final String FORWARD_PAGE
            = "/WEB-INF/jsp/listOfJourneys.jsp";

    private static final String LIST_OF_JOURNEY_ATTRIBUTE
            = "journeyList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);

        JourneyService journeyService
                = ServiceFactory.getInstance().getJoureyService();

//TODO when realising Per page veiw take limits from request
        request.setAttribute(LIST_OF_JOURNEY_ATTRIBUTE,
                journeyService.findAllActualForJourneyPage(0, 30));
        return FORWARD_PAGE;
    }
}
