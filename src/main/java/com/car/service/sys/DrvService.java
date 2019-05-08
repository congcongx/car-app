package com.car.service.sys;

import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DrvService {

    Result login(String openid, String dataSource, HttpServletRequest request, HttpServletResponse response);

    Result bind(Drv drv,String dataSource) throws Exception;

    /**
     * 查询搅拌车当班司机
     * @param mixerId
     * @return
     */
    Drv findDrvOndutyByMixerId(Integer mixerId);
}
