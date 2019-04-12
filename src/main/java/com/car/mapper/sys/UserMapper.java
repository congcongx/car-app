package com.car.mapper.sys;

import com.car.domain.sys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selAllUser();
}
