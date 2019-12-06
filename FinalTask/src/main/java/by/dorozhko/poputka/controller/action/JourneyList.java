package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JourneyList extends AllUsersAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);

        JourneyService journeyService
                = ServiceFactory.getInstance().getJoureyService();

//TODO when realising Per page veiw take limits from request
        request.setAttribute("journeyList",
                journeyService.findAllActualForJourneyPage(0, 30));
        return "/WEB-INF/jsp/listOfJourneys.jsp";
    }
}
