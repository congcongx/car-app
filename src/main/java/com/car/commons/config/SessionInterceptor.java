package com.car.commons.config;

import com.car.commons.constants.Const;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {
        Object drv = request.getSession().getAttribute(Const.SESSION_KEY);
        if(drv != null ) {
            return true;
        } else {
            throw new Exception("请先登录");
        }


    }

}
