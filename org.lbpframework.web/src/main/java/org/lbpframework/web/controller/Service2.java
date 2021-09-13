package org.lbpframework.web.controller;

import org.apache.commons.io.FileUtils;
import org.lbpframework.web.service.DownloadAbstractServiceTemplete;
import org.lbpframework.web.service.ResponseFile;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Component
public class Service2 extends DownloadAbstractServiceTemplete<UserVO> {

    @Override
    public ResponseFile run(UserVO body) {
        File file = new File("D:/简历-雷柏平-最新.pdf");
        try {
            return ResponseFile.builder()
                    .bytes(FileUtils.readFileToByteArray(file))
                    .fileName(file.getName()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
