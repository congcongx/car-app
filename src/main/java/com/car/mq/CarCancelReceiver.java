package com.car.mq;

import com.car.commons.constants.Const;
import com.car.commons.util.HttpClientUtil;
import com.car.commons.util.JsonUtil;
import com.car.domain.sys.MqMessageTemplate;
import com.car.domain.sys.WxMessageTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${mq.config.queue.cancel}",autoDelete="false"),
        exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.DIRECT),
        key = "${mq.config.queue.cancel.routing.key}"
))
public class CarCancelReceiver {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${wx.templateId}")
    private String templateId;

    @RabbitHandler
    public void process(String  msg) throws Exception {
        MqMessageTemplate mqMessageTemplate = JsonUtil.jsonStrToObj(msg, MqMessageTemplate.class);
        WxMessageTemplate wx = new WxMessageTemplate();
        wx.setTouser(mqMessageTemplate.getOpenid());
        wx.setTemplate_id(templateId);

        Map<String, Object> first = new HashMap<>();
        first.put("value", "该车已撤销生产！！");
        first.put("color", "#FF0000");

        Map<String,Object> keyword1 = new HashMap<>();
        keyword1.put("value",mqMessageTemplate.getDlvId());
        keyword1.put("color","#173177");

        Map<String,Object> keyword2 = new HashMap<>();
        keyword2.put("value",mqMessageTemplate.getFacShort());
        keyword2.put("color","#173177");

        Map<String,Object> keyword3 = new HashMap<>();
        keyword3.put("value",mqMessageTemplate.getMessage());
        keyword3.put("color","#173177");

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> keyword4 = new HashMap<>();
        keyword4.put("value",sdf.format(mqMessageTemplate.getTime()));
        keyword4.put("color","#173177");

        Map<String,Map<String,Object>> data = new HashMap<>();
        data.put("first",first);
        data.put("keyword1",keyword1);
        data.put("keyword2",keyword2);
        data.put("keyword3",keyword3);
        data.put("keyword4",keyword4);
        wx.setData(data);

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + redisTemplate.opsForValue().get(Const.WX_ACCESS_TOKEN_KEY);
        String s = HttpClientUtil.doPostJson(url, JsonUtil.objToJsonStr(wx));
    }
}

