package com.car.service.sys.impl;

import com.car.commons.constants.Const;
import com.car.commons.pojo.Result;
import com.car.commons.util.HttpClientUtil;
import com.car.domain.sys.Drv;
import com.car.domain.sys.Mixer;
import com.car.mapper.sys.DrvMapper;
import com.car.service.sys.DrvService;
import com.car.service.sys.MixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrvServiceImpl implements DrvService {

    @Autowired
    private DrvMapper drvMapper;

    @Autowired
    private MixerService mixerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

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


        String accessToken = redisTemplate.opsForValue().get(Const.WX_ACCESS_TOKEN_KEY);

        StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/user/info?access_token=");
        sb.append(accessToken).append("&openid=").append(openid).append("&lang=zh_CN");

        String userInfo = HttpClientUtil.doGet(sb.toString());
        Map<String,Object> map = new HashMap<>();

        List<Mixer> mixers = mixerService.selQaulifiedMixerByDrvId(drv.getDrvId());
        map.put("mixers",mixers);
        map.put("userInfo",userInfo);

        return Result.ok(map);
    }

    @Transactional(rollbackFor = Exception.class)
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
        Drv drv2 = drvMapper.selDrvByNameAndPhone(drv);
        if(drv2 == null) {
            return Result.error("请检查电话和姓名是否正确");
        }
        /**
         * 用户绑定微信号
         */
        drv2.setOpenid(openid);
        int i = drvMapper.updateByPrimaryKeySelective(drv2);
        if(i == 0) {
            return Result.error("绑定失败");
        }
        return Result.ok("绑定成功");
    }

    @Override
    public Drv findDrvOndutyByMixerId(Integer mixerId) {
        return drvMapper.selDrvOndutyByMixerId(mixerId);
    }
}
