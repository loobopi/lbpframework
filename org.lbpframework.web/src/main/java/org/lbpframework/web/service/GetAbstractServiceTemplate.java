package org.lbpframework.web.service;

import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.web.annotation.MethodType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MethodType(RequestMethod.GET)
public abstract class GetAbstractServiceTemplate<T> implements IAbstractService<T>{


    @Override
    public ResponseEntity execute(T body, HttpServletRequest request, HttpServletResponse response) {
        LoggerUtil.info(request.getRequestURI());
        return ResponseEntity.ok(this.run(body));
    }

    public abstract ServiceResponse run(T body);
}
