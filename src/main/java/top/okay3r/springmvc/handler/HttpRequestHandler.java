package top.okay3r.springmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:15
 * Explain:
 */
public interface HttpRequestHandler {
    void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
