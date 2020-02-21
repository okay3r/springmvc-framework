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
        //获取配置文件位置
        String location = config.getInitParameter("contextConfigLocation");
        //初始化IOC容器，在启动时读取全部的BeanDefinition
        initSpringContainer(location);
        //将所有所有的BeanDefinition都加载成bean
        beanFactory.getBeansByType(Object.class);
        //获取HandlerAdapter的实现类
        handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);
        //获取HandlerMapping的实现类
        handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);
    }

    //初始化IOC容器
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
            //根据请求获取对应handler
            Object handler = getHandler(req);
            //根据handler获取对应HandlerAdapter（适配器模式）
            HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
            if (handlerAdapter == null) {
                return;
            }
            //处理请求
            handlerAdapter.handleRequest(handler, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取处理器适配器
    private HandlerAdapter getHandlerAdapter(Object handler) {
        if (handlerAdapters != null && handlerAdapters.size() > 0) {
            //遍历处理器适配器集合
            for (HandlerAdapter handlerAdapter : handlerAdapters) {
                //判断该处理器适配器是否支持该处理器
                if (handlerAdapter.supports(handler)) {
                    return handlerAdapter;
                }
            }
        }
        return null;
    }


    //获取处理器
    private Object getHandler(HttpServletRequest req) {
        if (handlerMappings != null && handlerMappings.size() > 0) {
            //遍历处理器映射器集合
            for (HandlerMapping handlerMapping : handlerMappings) {
                //根据请求获取处理器
                Object handler = handlerMapping.getHandler(req);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }
}
