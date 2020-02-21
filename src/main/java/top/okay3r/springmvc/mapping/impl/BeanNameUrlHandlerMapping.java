package top.okay3r.springmvc.mapping.impl;

import top.okay3r.spring.framework.bean.aware.BeanFactoryAware;
import top.okay3r.spring.framework.bean.factory.support.DefaultListableBeanFactory;
import top.okay3r.spring.framework.beandefinition.BeanDefinition;
import top.okay3r.springmvc.mapping.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By okay3r.top
 * Author: okay3r
 * Date: 2020/2/21
 * Time: 12:18
 * Explain:
 */
public class BeanNameUrlHandlerMapping implements HandlerMapping, BeanFactoryAware {
    private Map<String, Object> urlHandlers = new HashMap<>();

    private DefaultListableBeanFactory beanFactory;


    public void init() {
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanDefinition.getBeanName();
            if (beanName.startsWith("/")) {
                urlHandlers.put(beanName, beanFactory.getBean(beanName));
            }
        }
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return this.urlHandlers.get(requestURI);
    }

    @Override
    public void setBeanFactory(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
