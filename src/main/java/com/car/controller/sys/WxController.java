package com.car.controller.sys;

import com.car.commons.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WxController {

    private static final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;

    @RequestMapping("/callback")
    public Object callback(HttpServletRequest request){
        String code = request.getParameter("code");
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token");
        url.append("?appid="+appid);
        url.append("&secret="+secret);
        url.append("&code="+code);
        url.append("&grant_type=authorization_code");
        String s = HttpClientUtil.doGet(url.toString());
        return s;
    }
}
