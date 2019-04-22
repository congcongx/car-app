package com.car.service.sys.impl;

import com.car.commons.constants.Const;
import com.car.domain.sys.Dlv;
import com.car.domain.sys.Drv;
import com.car.domain.sys.Line;
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

    @Override
    public Map<String, Object> findMixerQueue(Drv drv) {
        Map<String, Object> map = new HashMap<>();


        /**
         * 查询所有生产线
         */
        List<Line> lines = mixerMapper.selAllLine();
        /**
         * 查询生产线上的搅拌车
         */
        List<Dlv> dlvs = mixerMapper.selLineMixer();

        /**
         * 遍历生产线下面的车辆
         */
        for (Line line : lines) {
            boolean isFirst = true;
            for (Dlv dlv : dlvs) {
                if (line.getLineId().equals(dlv.getLineId())) {
                    //生产状态
                    if (Const.DLV_START.equals(dlv.getDlvStatus())) {
                        line.getProductMixerFcode().add(dlv.getMixerFcode());
                        continue;
                    }
                    //第一条为准备状态
                    if (isFirst) {
                        line.setReadyMixerFcode(dlv.getMixerFcode());
                        isFirst = false;
                        continue;
                    }
                    line.getWaitMixerFcode().add(dlv.getMixerFcode());
                }
            }
        }

        map.put("lines",lines);

        /**
         * 查询是否有发车的发货单
         */
        Dlv d = mixerMapper.selDlvByDrvCcode(drv.getDrvCcode());
        /**
         * 如果该司机有发货单
         */
        if(d != null) {
            map.put("titleInfo",true);
            map.put("lineMixer",d.getLineId()+"#"+d.getMixerFcode());
            map.put("plateNo",d.getPlateNo());
            /**
             * 获取司机排队位置
             */
            for(Line line : lines) {
                if(d.getLineId().equals(line.getLineId())) {
                    if(line.getProductMixerFcode().contains(d.getMixerFcode())) {
                        map.put("mixerIndex","当前排队：生产中");
                    }
                    if(d.getMixerFcode().equals(line.getReadyMixerFcode())) {
                        map.put("mixerIndex","当前排队：准备中");
                    }
                    if(line.getWaitMixerFcode().contains(d.getMixerFcode())) {
                        map.put("mixerIndex","当前排队：第"+ (line.getWaitMixerFcode().indexOf(d.getMixerFcode())+1) + "位");
                    }
                }
            }
        } else {
            map.put("titleInfo",false);
        }
        return  map;
    }

}
