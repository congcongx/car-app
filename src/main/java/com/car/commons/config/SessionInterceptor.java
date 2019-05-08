package com.car.commons.config;

import com.car.commons.constants.Const;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {

        String drvJson = redisTemplate.opsForValue().get(Const.SESSION_KEY + request.getHeader("token"));
        if(StringUtils.isNotEmpty(drvJson)) {
            return true;
        } else {
            throw new Exception("请先登录");
        }


    }

}
