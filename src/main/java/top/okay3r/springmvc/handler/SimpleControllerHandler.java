package top.okay3r.springmvc.handler;

import top.okay3r.springmvc.result.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:39
 * Explain:
 */
public interface SimpleControllerHandler {
    ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response);
}
