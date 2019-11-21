package by.dorozhko.poputka.controller.web;

import by.dorozhko.poputka.controller.action.ActionProvider;
import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
@MultipartConfig
public class ClientView extends HttpServlet {


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
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("HERE");

        logger.warn("HERE");

        String action = req.getParameter("action");
        logger.warn(action);


        System.out.println(action);
        RequestDispatcher requestDispatcher;
        if (action != null) {
            String page = ActionProvider.getInstance()
                    .getAction(action).execute(req, resp);
            requestDispatcher = req.getRequestDispatcher(page);
        } else {
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        }


        requestDispatcher.forward(req, resp);

    }


    //TODO init and destroy create Connectionpool and destroy Connectionpool realise using command and service!!!!!!!!!!!!
    @Override
    public void destroy() {
        super.destroy();
        ServiceFactory.getInstance().getConnectionService().closeConnectionPool();
        System.out.println("closing pool.");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServiceFactory.getInstance().getConnectionService().initConnectionPool();
        System.out.println("pool init with starting app");
    }


}
