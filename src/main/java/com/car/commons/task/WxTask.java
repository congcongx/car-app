package com.car.commons.task;

import com.car.commons.constants.Const;
import com.car.commons.util.HttpClientUtil;
import com.car.commons.util.JsonUtil;
import com.car.domain.sys.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WxTask {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;


    @Scheduled(cron="0 0/1 * * * ?")
    private void refreshAccessToken() {
        Long expire = redisTemplate.getExpire(Const.WX_ACCESS_TOKEN_KEY);
        if(expire < 300) {
            StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
            sb.append(appid).append("&secret=").append(secret);
            String s = HttpClientUtil.doGet(sb.toString());
            AccessToken accessToken = JsonUtil.jsonStrToObj(s, AccessToken.class);

            redisTemplate.opsForValue().set(Const.WX_ACCESS_TOKEN_KEY,accessToken.getAccess_token(),accessToken.getExpires_in(),TimeUnit.SECONDS);
        }
    }
}
