package com.car.service.sys;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 根据日期统计
     * @param date
     * @return
     */
    List<Map<String,Object>> countByDay(Integer drvCcode,String date) throws ParseException;

    /**
     * 根据月份统计
     * @param date
     * @return
     */
    List<Map<String,Object>> countByMonth(Integer drvCcode,String date) throws ParseException;
}
