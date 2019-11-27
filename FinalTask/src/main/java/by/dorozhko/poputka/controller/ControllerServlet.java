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

    /**
     * Path to the storage to upload xml file.
     */
    private static final String STORAGE_FOLDER_PATH =
            "WEB-INF/classes/data/xml";

    /**
     * buffer to read file size.
     */
    private static final int BUFFER = 1024;



    /**
     * Method take user request, take some information instead of user request
     * and send response to web view.
     *
     * @param req  user request.
     * @param resp response to user.
     * @throws ServletException servletException.
     * @throws IOException      IOException.
     */
    @Override
    protected void service(final HttpServletRequest req,
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
            String defaultAction = "default";
            page = ActionProvider.getInstance().getAction(defaultAction).execute(req, resp);
            requestDispatcher
                    = req.getRequestDispatcher(page);
        }


        requestDispatcher.forward(req, resp);

    }


    //TODO init and destroy create Connectionpool and destroy Connectionpool realise using command and service!!!!!!!!!!!!
    @Override
    public void destroy() {
        super.destroy();
        ServiceFactory.getInstance().getConnectionService().closeConnectionPool();
        logger.debug("closing pool.");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServiceFactory.getInstance().
                getConnectionService().initConnectionPool();
        logger.debug("Pool init with starting app");
    }


}
