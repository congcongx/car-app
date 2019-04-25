package com.car.controller.sys;

import com.car.commons.constants.Const;
import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;
import com.car.service.sys.DrvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/drv")
public class DrvController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DrvService drvService;

    @RequestMapping("/login")
    public Result login(String openid,String dataSource, HttpServletRequest request) {
        return drvService.login(openid,dataSource,request);
    }

    @RequestMapping("/bind")
    public Result bind(@RequestBody Drv drv,HttpServletRequest request) throws Exception {
        redisTemplate.opsForValue().set(Const.WX_ACCESS_TOKEN_KEY,"helloWorld",7200,TimeUnit.SECONDS);
        Thread.sleep(3000);
        Long expire = redisTemplate.getExpire(Const.WX_ACCESS_TOKEN_KEY, TimeUnit.SECONDS);
        System.out.println(expire);
        System.out.println(redisTemplate.opsForValue().get(Const.WX_ACCESS_TOKEN_KEY));
        return Result.ok("hello");
//        return drvService.bind(drv,drv.getDataSource());
    }
}
