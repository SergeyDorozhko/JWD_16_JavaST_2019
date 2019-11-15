package by.dorozhko.poputka.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/home")
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
         * @param req user request.
         * @param resp response to user.
         * @throws ServletException servletException.
         * @throws IOException IOException.
         */
        @Override
        protected void doGet(final HttpServletRequest req,
                             final HttpServletResponse resp)
                throws ServletException, IOException {
            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher("views/home.jsp");
            requestDispatcher.forward(req, resp);

        }

//        /**
//         * Method take user request, take some data instead of user request to send
//         * to the server for making some action and send response to web view.
//         * @param request user request.
//         * @param response response to user.
//         * @throws ServletException servletException.
//         * @throws IOException IOException.
//         */
//        @Override
//        protected void doPost(final HttpServletRequest request,
//                              final HttpServletResponse response)
//                throws ServletException, IOException {
//
//
//            final Part xmlFile = request.getPart("xmlFile");
//            final String fileName = getFileName(xmlFile);
//
//
//            OutputStream out = null;
//            InputStream filecontent = null;
//
//            try {
//                out = new FileOutputStream(new File(
//                        STORAGE_FOLDER_PATH + File.separator
//                                + fileName));
//                filecontent = xmlFile.getInputStream();
//
//                int read = 0;
//                final byte[] bytes = new byte[BUFFER];
//
//                while ((read = filecontent.read(bytes)) != -1) {
//                    out.write(bytes, 0, read);
//                }
//            } catch (FileNotFoundException fne) {
//                logger.error(fne);
//            } finally {
//                if (out != null) {
//                    out.close();
//                }
//                if (filecontent != null) {
//                    filecontent.close();
//                }
//
//            }
//
//
//            String pathXml = getServletContext().getResource("")
//                    .getPath() + STORAGE_FOLDER_PATH + File.separator + fileName;
//            String rootCatalog = getServletContext().getResource("").getPath();
//
//
//            String parser = request.getParameter("parser");
//            CommandProvider provider = CommandProvider.getInstance();
//            Command command = provider.getCommand(parser);
//
//            Set<TariffType> tariffsSet = null;
//
//            try {
//                tariffsSet = command.execute(
//                        parser + ";" + pathXml + ";" + rootCatalog);
//            } catch (ServiceException ex) {
//                request.setAttribute("error", "Not valid XML file");
//            }
//            request.setAttribute("parser", parser);
//            request.setAttribute("list", tariffsSet);
//            RequestDispatcher requestDispatcher
//                    = request.getRequestDispatcher("views/result.jsp");
//            requestDispatcher.forward(request, response);
//
//        }
//
//
//        private String getFileName(final Part part) {
//            for (String content : part.
//                    getHeader("content-disposition").split(";")) {
//                if (content.trim().startsWith("filename")) {
//                    return content.substring(content.indexOf('=') + 1).trim()
//                            .replace("\"", "");
//                }
//            }
//            return null;
//        }
//
//
//    }


}
