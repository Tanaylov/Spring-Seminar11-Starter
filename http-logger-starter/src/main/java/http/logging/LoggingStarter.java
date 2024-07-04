package http.logging;

import http.property.LoggingProperties;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class LoggingStarter implements Filter {

    private final LoggingProperties properties;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        logRequest(httpServletRequest);

        filterChain.doFilter(servletRequest, servletResponse);

        logResponse((HttpServletResponse) servletResponse, httpServletRequest);
    }

    private void logResponse(HttpServletResponse response, HttpServletRequest request) {

        doLog("response {}: status = {}", request.getServletPath(), response.getStatus());

    }

    private void logRequest(HttpServletRequest request) throws IOException{
        doLog("request {}", request.getServletPath());

        if (properties.isLogBody()) {
            try(BufferedInputStream is = new BufferedInputStream(request.getInputStream())) {
                String body = new String(is.readAllBytes());
                doLog("request body: {}", body);
            }
        }
    }

    private void doLog(String text, Object... param) {
        log.atLevel(properties.getLogLevel()).log(text, param);
    }

}
