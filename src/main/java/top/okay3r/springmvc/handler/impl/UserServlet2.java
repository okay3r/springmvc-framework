package top.okay3r.springmvc.handler.impl;

import top.okay3r.springmvc.handler.SimpleControllerHandler;
import top.okay3r.springmvc.result.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 13:13
 * Explain:
 */
public class UserServlet2 implements SimpleControllerHandler {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write("user2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
