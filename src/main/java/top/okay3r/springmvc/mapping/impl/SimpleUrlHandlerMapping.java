package top.okay3r.springmvc.mapping.impl;

import top.okay3r.springmvc.handler.impl.UserServlet2;
import top.okay3r.springmvc.mapping.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:33
 * Explain:
 */
public class SimpleUrlHandlerMapping implements HandlerMapping {
    private Map<String, Object> urlHandlers = new HashMap<String, Object>();

    /**
     * 初始化方法，需要配置bean标签的时候，指定一下。
     */
    public void init() {
        urlHandlers.put("/queryUser", new UserServlet2());
        urlHandlers.put("/addUser", null);
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return urlHandlers.get(uri);
    }
}
