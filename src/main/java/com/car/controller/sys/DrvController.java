package com.car.controller.sys;

import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;
import com.car.service.sys.DrvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/drv")
public class DrvController {

    @Autowired
    private DrvService drvService;

    @RequestMapping("/login")
    public Result login(String openid,String dataSource, HttpServletRequest request) {
        return drvService.login(openid,dataSource,request);
    }

    @RequestMapping("/bind")
    public Result bind(@RequestBody Drv drv,HttpServletRequest request) {
        return drvService.bind(drv,drv.getDataSource());
    }
}
