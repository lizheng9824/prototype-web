package net.itfans.prototype.web.demo1.common;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * サブセッションのFilter
 */
@Component
public class SubSessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // HttpServletRequestWrapperを差し替え
        SubSessionHttpServletRequestWrapper wrapper =
                new SubSessionHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(wrapper,  response);
    }
}
