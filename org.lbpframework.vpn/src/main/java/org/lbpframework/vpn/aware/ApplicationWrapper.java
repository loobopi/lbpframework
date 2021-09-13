package org.lbpframework.vpn.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationWrapper implements ApplicationContextAware {

    static ApplicationContext context = null;

    public static Object getBean(Class clazz){
        return context.getBean(clazz);
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
