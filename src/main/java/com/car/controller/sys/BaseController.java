package com.car.controller.sys;

import com.car.commons.constants.Const;
import com.car.commons.util.JsonUtil;
import com.car.domain.sys.Drv;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    protected Drv getDrv() {
        String token = this.getRequest().getHeader("token");
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        String drvJson = redisTemplate.opsForValue().get(Const.SESSION_KEY + token);
        if(StringUtils.isEmpty(drvJson)) {
            return null;
        }
        return JsonUtil.jsonStrToObj(drvJson,Drv.class);
    }


}
