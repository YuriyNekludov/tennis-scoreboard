package edu.tennis_scoreboard.filter;

import edu.tennis_scoreboard.exception.ErrorHandler;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            chain.doFilter(request, response);
        } catch (Throwable throwable) {
            ErrorHandler.handleError((HttpServletRequest) request, (HttpServletResponse) response, throwable);
        }
    }
}