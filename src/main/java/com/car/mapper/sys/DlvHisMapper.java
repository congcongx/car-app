package com.car.mapper.sys;

import java.util.List;
import java.util.Map;

public interface DlvHisMapper {

    /**
     * 根据日期统计
     * @param map
     * @return
     */
    List<Map<String,Object>> countTransVolsByDay(Map<String,Object> map);

    /**
     * 按照月统计
     * @param map
     * @return
     */
    List<Map<String,Object>> countTransVolsByMonth(Map<String,Object> map);
}
