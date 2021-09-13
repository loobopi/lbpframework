package org.lbpframework.web.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAbstractService<T> {

    public ResponseEntity execute(@RequestBody T body, HttpServletRequest request, HttpServletResponse response);


}
