package com.car;

import com.car.commons.enums.DataSourceEnum;
import com.car.commons.util.JsonUtil;
import com.car.domain.sys.WxMessageTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarAppApplicationTests {

    @Test
    public void contextLoads() {
        DataSourceEnum[] values = DataSourceEnum.values();
        for (DataSourceEnum d : values) {
            System.out.println(d.name());
        }
        System.out.println(values.toString());
    }

    public static void main(String[] args) {
        WxMessageTemplate wx = new WxMessageTemplate();

        Map<String,Map<String,Object>> data = new HashMap<>();
        Map<String,Object> first = new TreeMap<>();
        first.put("value","消息末班");
        first.put("color","#173177");
        data.put("first",first);

        Map<String,Object> keyword1 = new HashMap<>();
        keyword1.put("value","123456");
        keyword1.put("color","#173177");
        data.put("keyword1",keyword1);

        wx.setTouser("oST16w8IBlrqVICSdzwwFbB8crHw");
        wx.setTemplate_id("yGkGgR0u3nj1X5p6ZfamN2fMc9ts4hDUyjHDd6hEQ1k");
        wx.setData(data);

        String s = JsonUtil.objToJsonStr(wx);
        System.out.println(s);

    }

}
