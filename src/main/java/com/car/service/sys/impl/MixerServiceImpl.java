package com.car.service.sys.impl;

import com.car.domain.sys.Drv;
import com.car.domain.sys.Mixer;
import com.car.mapper.sys.DrvMapper;
import com.car.mapper.sys.MixerMapper;
import com.car.service.sys.MixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MixerServiceImpl implements MixerService {

    @Autowired
    private MixerMapper mixerMapper;

    @Autowired
    private DrvMapper drvMapper;

    @Override
    public Map<String, Object> findMixerInfo(Drv drv) {
        /**
         * 查询准驾司机
         */
        List<Drv> drvQaulifeds = drvMapper.selDrvQaulified(drv.getDrvId());

        /**
         * 查询搅拌车详情
         */
        Mixer mixer = mixerMapper.selMixerByDrvQaulified(drv.getDrvId());
        /**
         * 查询当班司机
         */
        Drv drvOnduty = drvMapper.selDrvOndutyByMixerId(mixer.getMixerId());

        Map map = new HashMap<String,Object>();
        map.put("drvQaulifeds",drvQaulifeds);
        map.put("mixer",mixer);
        map.put("drvOnduty",drvOnduty);
        return map;
    }

    @Override
    public int onDuty(Integer mixerId,Integer drvId) {
        return mixerMapper.insertDrvOnduty(mixerId,drvId);
    }

    @Override
    public int offDuty(Drv drv) {
        return mixerMapper.delDrvOnduty(drv.getDrvId());
    }

    @Override
    public Mixer findMixerByDrvQaulified(Integer drvId) {
        return mixerMapper.selMixerByDrvQaulified(drvId);
    }

}
