package com.car.service.sys;

import com.car.domain.sys.Drv;
import com.car.domain.sys.Line;
import com.car.domain.sys.Mixer;

import java.util.List;
import java.util.Map;

public interface MixerService {

    /**
     * 根据司机和车辆查询搅拌车详细信息
     * @param drv
     * @return
     */
    Map<String,Object> findMixerInfo(Drv drv,Integer mixerId);

    /**
     * 给车辆设置成当班司机
     */
    void onDuty(Integer mixerId,Integer drvId);


    /**
     * 司机交班
     */
    int offDuty(Integer drvId,Integer mixerId);


    /**
     * 查询搅拌车队列信息
     * @return
     */
    Map<String,Object> findMixerQueue(Drv drv,Integer mixerId);

    /**
     * 根据司机查询可以驾驶的搅拌车
     * @param drvId
     * @return
     */
    List<Mixer> selQaulifiedMixerByDrvId(Integer drvId);

    /**
     * 根据ID查询搅拌车
     * @param mixerId
     * @return
     */
    Mixer findMixerById(Integer mixerId);

    /**
     * 查询所有生产线信息
     * @return
     */
    List<Line> findAllLine();
}
