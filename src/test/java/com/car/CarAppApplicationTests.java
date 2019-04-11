package com.car;

import com.car.commons.enums.DataSourceKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarAppApplicationTests {

    @Test
    public void contextLoads() {
        DataSourceKey[] values = DataSourceKey.values();
        for (DataSourceKey d : values) {
            System.out.println(d.name());
        }
        System.out.println(values.toString());
    }

}
