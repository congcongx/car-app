package com.car.service.sys;

import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;

import javax.servlet.http.HttpServletRequest;

public interface DrvService {

    Result login(String openid, String dataSource, HttpServletRequest request);

    Result bind(Drv drv,String dataSource);
}
