package by.dorozhko.poputka.controller;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    public static final String CHARACTER_ENCODING = "UTF-8";

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(CHARACTER_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
