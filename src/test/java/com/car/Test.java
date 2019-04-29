package com.car;

import com.car.commons.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String date = "2019-04-02";
        Date nowTime = DateUtil.strToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(Calendar.DAY_OF_MONTH,-6);
        String s = DateUtil.dateToStr(calendar.getTime());
        System.out.println(s);
    }
}
