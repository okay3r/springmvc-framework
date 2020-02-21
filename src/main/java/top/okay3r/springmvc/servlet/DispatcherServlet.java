package top.okay3r.springmvc.servlet;

import top.okay3r.spring.framework.bean.factory.support.DefaultListableBeanFactory;
import top.okay3r.spring.framework.reader.XmlBeanDefinitionReader;
import top.okay3r.spring.framework.resource.ClasspathResource;
import top.okay3r.springmvc.adapter.HandlerAdapter;
import top.okay3r.springmvc.mapping.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 11:38
 * Explain:
 */
public class DispatcherServlet extends AbstractServlet {
    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();
    private DefaultListableBeanFactory beanFactory;

    public void init(ServletConfig config) {
        String location = config.getInitParameter("contextConfigLocation");
        initSpringContainer(location);
        beanFactory.getBeansByType(Object.class);
        handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);
        handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);
    }

    public void initSpringContainer(String location) {
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        ClasspathResource resource = new ClasspathResource(location);
        InputStream inputStream = resource.getResource();
        xmlBeanDefinitionReader.loadBeanDefinitions(inputStream);
    }

    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Object handler = getHandler(req);
            HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
            if (handlerAdapter == null) {
                return;
            }
            handlerAdapter.handleRequest(handler, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        if (handlerAdapters != null && handlerAdapters.size() > 0) {
            for (HandlerAdapter handlerAdapter : handlerAdapters) {
                if (handlerAdapter.supports(handler)) {
                    return handlerAdapter;
                }
            }
        }
        return null;
    }


    private Object getHandler(HttpServletRequest req) {
        if (handlerMappings != null && handlerMappings.size() > 0) {
            for (HandlerMapping handlerMapping : handlerMappings) {
                Object handler = handlerMapping.getHandler(req);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }
}
