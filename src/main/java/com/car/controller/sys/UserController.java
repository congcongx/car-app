package com.car.controller.sys;

import com.car.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public Object hello(String dataSource) {
        String login = userService.login(dataSource);
        return "hello";
    }
}
