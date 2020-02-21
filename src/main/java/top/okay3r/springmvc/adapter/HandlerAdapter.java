package top.okay3r.springmvc.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 11:45
 * Explain:
 */
public interface HandlerAdapter {
    void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;

    boolean supports(Object handler);
}
