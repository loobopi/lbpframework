package org.lbpframework.web.controller;

import lombok.Builder;
import lombok.Getter;
import org.lbpframework.web.service.ServiceResponse;

@Getter
public class UserResponse extends ServiceResponse {

    TestVO testVO;

    @Builder
    public UserResponse(String rspCode, String rspMsg, TestVO testVO) {
        super(rspCode, rspMsg);
        this.testVO = testVO;
    }
}
