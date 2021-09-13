package org.lbpframework.web.mappings;

import org.lbpframework.web.annotation.MethodType;
import org.lbpframework.web.service.IAbstractService;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@Component
public class ServiceRequestMappingHandlerMapping extends RequestMappingHandlerMapping implements MatchableHandlerMapping,EmbeddedValueResolverAware {

    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();
    @Override
    protected void initHandlerMethods() {
        super.initHandlerMethods();
    }

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return IAbstractService.class.isAssignableFrom(beanType);
    }

    @Override
    protected void detectHandlerMethods(Object handler) {
        if(handler instanceof String){
            System.out.println(handler);
        }
        Class<?> handlerType = handler instanceof String ? this.getApplicationContext().getType((String)handler) : handler.getClass();
        Method executeM = ReflectionUtils.findMethod(handlerType, "execute", (Class[])null);
        String serviceName = "";
        String serviceAddr = "";
        if (serviceName == null) {
            serviceAddr = handlerType.getSimpleName();
        } else {
            serviceAddr = serviceName + "/" + handlerType.getSimpleName();
        }
        this.registerHandlerMethod(handler, executeM, this.createRequestMappingInfo(handlerType, executeM, serviceAddr));
    }

    private RequestMappingInfo createRequestMappingInfo(Class handlerType,AnnotatedElement element, String className) {
        RequestMapping requestMapping = (RequestMapping) AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        if (element instanceof Class) {
            this.getCustomTypeCondition((Class)element);
        } else {
            this.getCustomMethodCondition((Method)element);
        }

        return requestMapping != null ? this.createRequestMappingInfo(requestMapping, (Method)element, className) :
                RequestMappingInfo.paths( "/" + className).methods(getRequestMethod(handlerType)).build();
    }

    protected RequestMappingInfo createRequestMappingInfo(RequestMapping requestMapping, Method method, String className) {
        RequestMappingInfo.Builder builder = RequestMappingInfo.paths(new String[]{className}).methods(new RequestMethod[]{RequestMethod.POST}).params(requestMapping.params()).headers(requestMapping.headers()).consumes(requestMapping.consumes()).produces(requestMapping.produces()).mappingName(requestMapping.name());
        return builder.options(this.config).build();
    }

    private RequestMethod getRequestMethod(Class handlerType){
        Class superclass = handlerType.getSuperclass();
        if(superclass.isAnnotationPresent(MethodType.class)){
            MethodType methodType = (MethodType)superclass.getAnnotation(MethodType.class);
            return methodType.value();
        }
        throw new RuntimeException("MethodType 不能为空");
    }

    /**
     * 属性设置
     */
    @Override
    public void afterPropertiesSet() {
        // 提升当前 HandlerMapping 的在映射处理器列表中的顺序
        super.setOrder(0);
        super.afterPropertiesSet();
    }
}
