package com.car.service.sys.impl;

import com.car.commons.util.DateUtil;
import com.car.commons.util.ModelArithUtil;
import com.car.mapper.sys.DlvHisMapper;
import com.car.service.sys.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private DlvHisMapper dlvHisMapper;


    @Override
    public List<Map<String, Object>> countByDay(Integer drvCcode,String date) throws ParseException {
        /**
         * 一周前的日期
         */
        String ofWeek = DateUtil.getDay(DateUtil.dateSub(DateUtil.strToDate(date),-6));
        String nextDay = DateUtil.getDay(DateUtil.dateSub(DateUtil.strToDate(date),1));
        List<Integer> nums = ModelArithUtil.deliveryTeayMonthList(8, ofWeek, date);
        Map<String,Object> param = new HashMap<>();
        param.put("weekTime",ofWeek);
        param.put("nextDay",nextDay);
        param.put("nums",nums);
        param.put("drvCcode",drvCcode);
        return dlvHisMapper.countTransVolsByDay(param);
    }

    @Override
    public List<Map<String, Object>> countByMonth(Integer drvCcode, String date) throws ParseException {
        String sixMonth = DateUtil.getDay(DateUtil.dateMonthOperator(DateUtil.strToDate(date),-5));
        String nextMonth = DateUtil.getDay(DateUtil.dateMonthOperator(DateUtil.strToDate(date),1));
        List<Integer> nums = ModelArithUtil.deliveryTeayMonthList(8, sixMonth, nextMonth);
        Map<String,Object> param = new HashMap<>();
        param.put("weekTime",sixMonth);
        param.put("nextDay",nextMonth);
        param.put("nums",nums);
        param.put("drvCcode",drvCcode);
        return dlvHisMapper.countTransVolsByMonth(param);
    }
}
