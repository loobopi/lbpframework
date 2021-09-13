package org.lbpframework.web.service;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MultipartFileVO{

    MultipartFile file;
}
