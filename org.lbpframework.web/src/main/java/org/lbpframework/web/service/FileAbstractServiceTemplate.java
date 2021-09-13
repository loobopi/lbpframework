package org.lbpframework.web.service;

import org.lbpframework.logging.LoggerUtil;
import org.lbpframework.web.annotation.MethodType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@MethodType(RequestMethod.POST)
public abstract class FileAbstractServiceTemplate implements IAbstractService<MultipartFile[]>{

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity execute(@RequestParam(value="file") MultipartFile[] file, HttpServletRequest request, HttpServletResponse response) {
        LoggerUtil.info(request.getRequestURI());
        Map<String, String[]> parameterMap = request.getParameterMap();
        return ResponseEntity.ok(this.run(file,parameterMap));
    }

    public abstract ServiceResponse run(MultipartFile[] file,Map<String,String[]> requestParameterMap);
}
