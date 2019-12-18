package by.dorozhko.poputka.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ActionFromUriFilter implements Filter {

    public static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private final Logger logger = LogManager.getLogger(getClass().getName());


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();

            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }

            logger.debug(actionName);
            try {
                Action action = ActionProvider.getInstance()
                        .getAction(actionName);
                logger.debug(String.format("get action ok %s", action));
                httpRequest.setAttribute("action", action);
                logger.debug("set atribut ok");

                filterChain.doFilter(servletRequest, servletResponse);
                logger.debug("go net filter ok");
            } catch (NullPointerException e) {
                logger.error("It is impossible to create action handler object", e);
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext()
                        .getRequestDispatcher(ERROR_PAGE)
                        .forward(servletRequest, servletResponse);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            servletRequest.getServletContext()
                    .getRequestDispatcher(ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }
    }

}
