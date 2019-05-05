package com.car.service.sys;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 根据日期统计
     * @return
     */
    List<Map<String,Object>> countByDay(Integer drvCcode,String startDate,String endDate) throws ParseException;

    /**
     * 根据月份统计
     * @return
     */
    List<Map<String,Object>> countByMonth(Integer drvCcode,String endDate) throws ParseException;


    /**
     * 根据年份统计
     * @param drvCcode
     * @param endDate
     * @return
     */
    List<Map<String,Object>> countByYear(Integer drvCcode,String endDate);

    /**
     * 聚合信息分类汇总
     * @param type
     * @return
     */
    Map<String,Object> countByType(Integer drvCcode,String crtTime,String type);
}
