package com.car.commons.config;


import com.car.commons.constants.Const;
import com.car.commons.enums.DataSourceKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    private static final String[] IS_LOGIN = {"login","bind"};

    //配置service切点
    @Pointcut("execution( * com.car.service..* (..) )")
    public void aspect(){
        System.out.println("aaaaaaaaaaaaaa");
    }


    @Before("aspect()")
    public void switchDataSource(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Boolean isLoginMethod = isLoginMethod(methodName);
        if(isLoginMethod) {
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            for(int i = 0; i <parameterNames.length; i++) {
                if(Const.DATASOURCE.equals(parameterNames[i])){
                    String dataSource = (String) joinPoint.getArgs()[i];
                    for(DataSourceKey dataSourceKey: DataSourceKey.values()) {
                        if (dataSourceKey.name().equals(dataSource)) {
                            DataSourceContextHolder.setDataSourceKey(dataSourceKey.name());
                            return;
                        }
                    }
                }
            }
        }
    }



    @After("aspect()")
    public void restoreDataSource(JoinPoint point) {
        DataSourceContextHolder.clearDataSourceKey();
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

