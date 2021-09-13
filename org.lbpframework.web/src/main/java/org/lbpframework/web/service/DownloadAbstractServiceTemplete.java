package org.lbpframework.web.service;

import org.lbpframework.web.annotation.MethodType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@MethodType(RequestMethod.GET)
public abstract class DownloadAbstractServiceTemplete<T> implements IAbstractService<T> {

    @Override
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity execute(T body, HttpServletRequest request, HttpServletResponse response) {
        ResponseFile responseFile = this.run(body);
        String fileNameSuffix = responseFile.getFileName().split("\\.")[1];
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + Objects.hash(fileNameSuffix) + "." + fileNameSuffix + "\"")
                .body(responseFile.bytes);
    }

    public abstract ResponseFile run(T body);
}
