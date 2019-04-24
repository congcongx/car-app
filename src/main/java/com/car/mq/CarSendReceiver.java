package com.car.mq;

import com.car.commons.util.JsonUtil;
import com.tongyun.mes.entity.WxMessageTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${mq.config.queue.send}",autoDelete="false"),
        exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.DIRECT),
        key = "${mq.config.queue.send.routing.key}"
))
public class CarSendReceiver {

    @RabbitHandler
    public void process(String  msg) throws Exception {
        System.out.println("接收到的消息："+msg);
        WxMessageTemplate wxMessageTemplate = JsonUtil.jsonStrToObj(msg, WxMessageTemplate.class);
        System.out.println("序列化后的对象"+wxMessageTemplate);
    }
}
