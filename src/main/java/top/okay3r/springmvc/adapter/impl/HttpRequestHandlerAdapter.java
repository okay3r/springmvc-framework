package top.okay3r.springmvc.adapter.impl;

import top.okay3r.springmvc.adapter.HandlerAdapter;
import top.okay3r.springmvc.handler.HttpRequestHandler;
import top.okay3r.springmvc.handler.SimpleControllerHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:35
 * Explain:
 */
public class HttpRequestHandlerAdapter implements HandlerAdapter {
    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //适配器模式调用对应处理器
        ((HttpRequestHandler) handler).handleRequest(request, response);
    }

    @Override
    public boolean supports(Object handler) {
        //判断其类型，即判断该处理器是否属于本处理器的类型
        return handler instanceof HttpRequestHandler;
    }
}
