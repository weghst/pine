package com.weghst.pine.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kevin Zou
 */
@WebServlet(urlPatterns = "/errorHandler.servlet")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        int statusCode = (int) req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");

        if (500 == statusCode) {
            resp.sendRedirect("/500.html");
        } else if (404 == statusCode) {
            resp.sendRedirect("/404.html");
        } else if (403 == statusCode) {
            resp.sendRedirect("/403.html");
        } else {
            // FIXME 需要优化处理
            System.out.println("Warning.............................................");
        }
    }
}
