package top.okay3r.springmvc.handler.impl;

import top.okay3r.springmvc.handler.HttpRequestHandler;
import top.okay3r.springmvc.handler.SimpleControllerHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:53
 * Explain:
 */
public class UserServlet implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=utf8");
        response.getWriter().write("你好hello");
    }
}
