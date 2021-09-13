package org.lbpframework.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
@RestController
@RequestMapping("/abstract")
@Api(tags = "用户管理相关接口")*/
public class AbstractController {

    /**
     * 演示swagger参数没有封装的写法
     *
     * @param username
     * @param weChat
     * @param address
     * @return
     */
    @PostMapping("/addUser")
    public String addUser(@RequestParam(required = true) String username,
                                String weChat,
                                String address) {
        System.out.println("username=" + username);
        System.out.println("weChat=" + weChat);
        System.out.println("address=" + address);
        return "";
    }
}
