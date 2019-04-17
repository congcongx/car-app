package com.car.service.sys.impl;

import com.car.commons.constants.Const;
import com.car.commons.pojo.Result;
import com.car.domain.sys.Drv;
import com.car.mapper.sys.DrvMapper;
import com.car.service.sys.DrvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class DrvServiceImpl implements DrvService {

    @Autowired
    private DrvMapper drvMapper;

    @Override
    public Result login(String openid, String dataSource, HttpServletRequest request) {

        Drv drv = drvMapper.selDrvByOpenid(openid);
        if(drv == null) {
            return Result.build(Result.REDIRECT_CODE,"您未在该站点绑定账号");
        }
        if(Const.USEABLE_FREEZE.equals(drv.getUseable())) {
            return Result.error("该用户已被冻结");
        }
        drv.setDataSource(dataSource);
        request.getSession().setAttribute(Const.SESSION_KEY,drv);
        return Result.ok("登录成功");
    }

    @Override
    public Result bind(Drv drv, String dataSource) throws Exception {
        String openid = drv.getOpenid();
        /**
         * 校验该账号是否绑定
         */
        Drv drv1 = drvMapper.selDrvByOpenid(openid);
        if(drv1 != null) {
            return Result.error("微信号已绑定该站点");
        }
        /**
         * 校验身份证和姓名是否正确
         */
        Drv drv2 = drvMapper.selDrvByNameAndIdcard(drv);
        if(drv2 == null) {
            return Result.error("请检查身份证和姓名是否正确");
        }
        /**
         * 用户绑定微信号
         */
        drv2.setOpenid(openid);
        int i = drvMapper.updateByPrimaryKeySelective(drv2);
        if(i == 0) {
            return Result.error("绑定失败");
        }
        if(i > 0) {
            throw new Exception("错误");
        }
        return Result.ok("绑定成功");
    }

    @Override
    public Drv findDrvOndutyByMixerId(Integer mixerId) {
        return drvMapper.selDrvOndutyByMixerId(mixerId);
    }
}
