package org.lbpframework.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lbpframework.web.service.MultipartFileVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipartFileVO1 extends MultipartFileVO {

    String key1;
    String key2;
}
