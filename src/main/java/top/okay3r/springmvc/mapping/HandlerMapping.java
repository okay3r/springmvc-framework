package top.okay3r.springmvc.mapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 11:45
 * Explain:
 */
public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
