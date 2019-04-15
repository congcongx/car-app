package com.car.mapper.sys;

import com.car.domain.sys.Drv;

public interface DrvMapper {
    int deleteByPrimaryKey(Integer drvId);

    int insert(Drv record);

    int insertSelective(Drv record);

    Drv selectByPrimaryKey(Integer drvId);

    int updateByPrimaryKeySelective(Drv record);

    int updateByPrimaryKey(Drv record);

    /**
     * 根据openid查询司机
     * @param openid
     * @return
     */
    Drv selDrvByOpenid(String openid);

    /**
     * 根据身份证和姓名查询司机
     */
    Drv selDrvByNameAndIdcard(Drv drv);
}