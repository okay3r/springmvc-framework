package top.okay3r.springmvc.adapter.impl;

import top.okay3r.springmvc.adapter.HandlerAdapter;
import top.okay3r.springmvc.handler.SimpleControllerHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:44
 * Explain:
 */
public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ((SimpleControllerHandler) handler).handleRequest(request, response);
    }

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleControllerHandler);
    }

}
