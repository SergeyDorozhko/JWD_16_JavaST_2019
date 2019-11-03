package by.dorozhko.xmlparse.demowebhelloworld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class Demoweb extends HttpServlet {
    /**
     * Method take user request, take some information instead of user request
     * and send response to web view.
     * @param request user request.
     * @param response response to user.
     * @throws ServletException servletException.
     * @throws IOException IOException.
     */
    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World! Sergey</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World! !@#Sergey</h1>");
        out.println("</body>");
        out.println("</html>");
    }

}
