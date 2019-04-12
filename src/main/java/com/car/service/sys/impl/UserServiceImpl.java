package com.car.service.sys.impl;

import com.car.domain.sys.User;
import com.car.mapper.sys.UserMapper;
import com.car.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String dataSource) {
//        DataSourceContextHolder.setDataSourceKey(dataSource);
        List<User> users = userMapper.selAllUser();
        System.out.println(users);
        return dataSource;
    }
}
