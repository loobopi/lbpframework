package org.lbpframework.web.controller;

import org.lbpframework.web.service.FileAbstractServiceTemplate;
import org.lbpframework.web.service.ServiceResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Component
public class Service3 extends FileAbstractServiceTemplate{

    @Override
    public ServiceResponse run(MultipartFile[] file, Map<String, String[]> parameterMaps) {
        String[] key1s = parameterMaps.get("key1");
        return null;
    }

}
