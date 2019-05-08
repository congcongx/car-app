package com.car.commons.config;


import com.car.commons.constants.Const;
import com.car.commons.enums.DataSourceEnum;
import com.car.commons.util.JsonUtil;
import com.car.domain.sys.Drv;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    private static final String[] IS_LOGIN = {"login","bind"};

    @Autowired
    private StringRedisTemplate redisTemplate;

    //配置service切点
    @Pointcut("execution( * com.car.service..* (..) )")
    public void aspect(){ }

    @Before("aspect()")
    public void switchDataSource(JoinPoint joinPoint) throws Exception {
        String methodName = joinPoint.getSignature().getName();
        Boolean isLoginMethod = isLoginMethod(methodName);
        if(isLoginMethod) {
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            for(int i = 0; i <parameterNames.length; i++) {
                if(Const.DATASOURCE.equals(parameterNames[i])){
                    String dataSource = (String) joinPoint.getArgs()[i];
                    DataSourceContextHolder.setDataSourceEnum(DataSourceEnum.valueOf(dataSource));
                    return;
                }
            }
        }

        String token = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest()
                .getHeader("token");

        String drvJson = redisTemplate.opsForValue().get(Const.SESSION_KEY + token);

        if(StringUtils.isEmpty(drvJson)) {
            throw new Exception("非法请求");
        }

        Drv drv = JsonUtil.jsonStrToObj(drvJson, Drv.class);
        redisTemplate.expire(Const.SESSION_KEY+token,1800,TimeUnit.SECONDS);
        DataSourceContextHolder.setDataSourceEnum(DataSourceEnum.valueOf(drv.getDataSource()));

    }



    @After("aspect()")
    public void restoreDataSource(JoinPoint point) {
        DataSourceContextHolder.clearDataSourceEnum();
    }



    private Boolean isLoginMethod(String methodName) {
        for(String login : IS_LOGIN) {
            if(methodName.contains(login)) {
                return true;
            }
        }
        return false;
    }
}

