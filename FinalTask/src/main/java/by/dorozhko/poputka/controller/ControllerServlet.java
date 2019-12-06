package by.dorozhko.poputka.controller;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class ControllerServlet extends HttpServlet {


    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Method take user request, take some information instead of user request
     * and send response to web view.
     *
     * @param req  user request.
     * @param resp response to user.
     * @throws ServletException servletException.
     * @throws IOException      IOException.
     */
    protected void process(final HttpServletRequest req,
                           final HttpServletResponse resp)
            throws ServletException, IOException {


        Action action = (Action) req.getAttribute("action");

        logger.debug(action);


        String page = null;
        RequestDispatcher requestDispatcher;
        if (action != null) {
            page = action.execute(req, resp);
            requestDispatcher = req.getRequestDispatcher(page);
        } else {
            logger.debug("go to main page");
            String defaultAction = "default";
            page = ActionProvider.getInstance().getAction(defaultAction).execute(req, resp);
            requestDispatcher
                    = req.getRequestDispatcher(page);
        }

        if (page.contains(".jsp")) {
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }

    }


    @Override
    public void destroy() {
        super.destroy();
        ServiceFactory.getInstance().getConnectionService().closeConnection();
        logger.debug("closing pool.");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServiceFactory.getInstance().
                getConnectionService().initConnection();
        logger.debug("Pool init with starting app");
    }


}
