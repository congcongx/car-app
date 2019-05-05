package com.car.service.sys.impl;

import com.car.commons.constants.Const;
import com.car.commons.util.DateUtil;
import com.car.commons.util.ModelArithUtil;
import com.car.mapper.sys.DlvHisMapper;
import com.car.service.sys.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private DlvHisMapper dlvHisMapper;


    @Override
    public List<Map<String, Object>> countByDay(Integer drvCcode,String startDate,String endDate) throws ParseException {
        /**
         * 一周前的日期
         */
//        String ofWeek = DateUtil.getDay(DateUtil.dateSub(DateUtil.strToDate(date),-6));
        String nextDay = DateUtil.getDay(DateUtil.dateSub(DateUtil.strToDate(endDate),1));
        List<Integer> nums = ModelArithUtil.deliveryTeayMonthList(8, startDate, endDate);
        Map<String,Object> param = new HashMap<>();
        param.put("startDate",startDate);
        param.put("endDate",nextDay);
        param.put("nums",nums);
        param.put("drvCcode",drvCcode);
        return dlvHisMapper.countTransVolsByDay(param);
    }

    @Override
    public List<Map<String, Object>> countByMonth(Integer drvCcode, String endDate) throws ParseException {
        String sixMonth = DateUtil.getDay(DateUtil.dateMonthOperator(DateUtil.strToDate(endDate),-5));
        List<Integer> nums = ModelArithUtil.deliveryTeayMonthList(8, sixMonth, endDate);
        Map<String,Object> param = new HashMap<>();
        param.put("sixMonth",sixMonth);
        param.put("endDate",endDate);
        param.put("nums",nums);
        param.put("drvCcode",drvCcode);
        return dlvHisMapper.countTransVolsByMonth(param);
    }

    @Override
    public List<Map<String, Object>> countByYear(Integer drvCcode, String endDate) {
        Map<String,Object> param = new HashMap<>();
        param.put("nums",ModelArithUtil.generateList(8));
        param.put("drvCcode",drvCcode);
        return dlvHisMapper.countTransVolsByYear(param);
    }

    @Override
    public Map<String, Object> countByType(Integer drvCcode,String crtTime, String type) {
        String date = DateUtil.stampToDateStr(DateUtil.strToDateByType(crtTime,type).getTime(), "yyyy-MM-dd");
        String datePattern = DateUtil.getDatePattern(type);

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("drvCcode",drvCcode);
        param.put("crtTime",date);
        param.put("datePattern",datePattern);

        List<Integer> nums = new ArrayList<>();
        nums.add(ModelArithUtil.deliveryYearMonth(8, date));
        if(Const.YEAR.equals(type)) {
            nums = ModelArithUtil.generateList(8);
        }
        param.put("nums",nums);
        return dlvHisMapper.countSumByCrtTime(param);
    }
}
