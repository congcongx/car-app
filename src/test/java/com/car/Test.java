package com.car;

import com.car.commons.util.DateUtil;
import com.car.commons.util.ModelArithUtil;

import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String date = "2019-05-02";
        int i = ModelArithUtil.deliveryYearMonth(8, date);
        System.out.println(i);
        Date nowTime = DateUtil.strToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(Calendar.MONTH,-6);
        String s = DateUtil.dateToStr(calendar.getTime());
        System.out.println(s);
    }
}
