package com.car.service.sys;

import com.car.domain.sys.Drv;
import com.car.domain.sys.Mixer;

import java.util.Map;

public interface MixerService {

    /**
     * 根据司机查询搅拌车详细信息
     * @param drv
     * @return
     */
    Map<String,Object> findMixerInfo(Drv drv);

    /**
     * 给车辆设置成当班司机
     */
    int onDuty(Integer mixerId,Integer drvId);


    /**
     * 司机交班
     * @param drv
     */
    int offDuty(Drv drv);

    /**
     * 根据准驾司机查询所驾驶的搅拌车
     * @param drvId
     * @return
     */
    Mixer findMixerByDrvQaulified(Integer drvId);


}
