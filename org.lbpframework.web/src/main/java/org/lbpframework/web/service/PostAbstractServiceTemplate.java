package org.lbpframework.web.service;


import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.web.annotation.MethodType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MethodType(RequestMethod.POST)
public abstract class PostAbstractServiceTemplate<T> implements IAbstractService<T>{

    @Override
    @ResponseBody
    public ResponseEntity execute(@RequestBody T body, HttpServletRequest request, HttpServletResponse response) {

        LoggerUtil.info(request.getRequestURI());
        return null;
    }
}
