package org.lbpframework.web.controller;

import lombok.Builder;
import lombok.Getter;
import org.lbpframework.web.service.ServiceResponse;

@Getter
public class UserVO extends ServiceResponse {

    private String userId;
    private String password;

    public UserVO() {
    }

    @Builder
    public UserVO(String rspCode, String rspMsg, String userId, String password) {
        super(rspCode, rspMsg);
        this.userId = userId;
        this.password = password;
    }
}
