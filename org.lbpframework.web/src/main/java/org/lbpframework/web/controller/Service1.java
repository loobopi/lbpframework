package org.lbpframework.web.controller;

import org.lbpframework.web.service.GetAbstractServiceTemplate;
import org.springframework.stereotype.Component;

@Component
public class Service1 extends GetAbstractServiceTemplate<UserVO> {

    @Override
    public UserResponse run(UserVO body) {
        return UserResponse.builder()
                .rspCode("100")
                .rspMsg("返回成功")
                .testVO(new TestVO("userId","leiboping"))
                .build();
    }
}
