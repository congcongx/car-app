package com.car.controller.sys;

import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;
import com.car.service.sys.DrvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/drv")
public class DrvController extends BaseController{

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DrvController.class);

    @Autowired
    private DrvService drvService;

    @RequestMapping("/login")
    public Result login(String openid, String dataSource, HttpServletRequest request, HttpServletResponse response) {
        return drvService.login(openid,dataSource,request,response);
    }

    @RequestMapping("/bind")
    public Result bind(@RequestBody Drv drv,HttpServletRequest request) throws Exception {
        return drvService.bind(drv,drv.getDataSource());
    }

}
