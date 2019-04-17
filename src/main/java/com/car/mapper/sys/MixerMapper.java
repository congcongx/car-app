package com.car.mapper.sys;

import com.car.domain.sys.Mixer;

public interface MixerMapper {
    int deleteByPrimaryKey(Integer mixerId);

    int insert(Mixer record);

    int insertSelective(Mixer record);

    Mixer selectByPrimaryKey(Integer mixerId);

    int updateByPrimaryKeySelective(Mixer record);

    int updateByPrimaryKey(Mixer record);

    /**
     * 根据准驾司机查询搅拌车详情
     * @param drvId
     * @return
     */
    Mixer selMixerByDrvQaulified(Integer drvId);


    int insertDrvOnduty(Integer mixerId,Integer drvId);

    int delDrvOnduty(Integer mixerId);
}