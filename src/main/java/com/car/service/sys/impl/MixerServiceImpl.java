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
    public Map<String, Object> findMixerInfo(Drv drv,Integer mixerId) {
        /**
         * 查询准驾司机
         */
        List<Drv> drvQaulifeds = drvMapper.selDrvQaulified(drv.getDrvId(),mixerId);

        /**
         * 查询搅拌车详情
         */
        Mixer mixer = mixerMapper.selectByPrimaryKey(mixerId);
        /**
         * 查询当班司机
         */
        Drv drvOnduty = null;
        if(mixer != null) {
            drvOnduty = drvMapper.selDrvOndutyByMixerId(mixer.getMixerId());
        }

        Map map = new HashMap<String,Object>();

        //如果存在当班司机
        if(drvOnduty != null) {
            //如果本人是当班司机
            if(drvOnduty.getDrvId().equals(drv.getDrvId())) {
                map.put("onDutyBtn",false);
                map.put("offDutyBtn",true);
            } else {
                map.put("onDutyBtn",false);
                map.put("offDutyBtn",false);
            }
        } else {
            map.put("onDutyBtn",true);
            map.put("offDutyBtn",false);
        }

        map.put("drvQaulifeds",drvQaulifeds);
        map.put("mixer",mixer);
        map.put("drvOnduty",drvOnduty);
        return map;
    }

    @Override
    public void onDuty(Integer mixerId,Integer drvId) {
          this.mixerMapper.insertDrvOnduty(mixerId,drvId);
    }

    @Override
    public int offDuty(Integer drvId,Integer mixerId) {
        return mixerMapper.delDrvOnduty(drvId,mixerId);
    }

    @Override
    public Map<String, Object> findMixerQueue(Drv drv,Integer mixerId) {
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

        Mixer mixer = this.findMixerById(mixerId);

        Dlv d = null;
        /**
         * 查询是否有发车的发货单
         */
        if(mixer != null) {
           d = mixerMapper.selDlvByDrvCcodeAndMixerFcode(drv.getDrvCcode(),mixer.getMixerFcode());
        }
        /**
         * 如果该司机有发货单
         */
        if(d != null) {
            map.put("titleInfo",true);
            map.put("lineMixer",d.getLineId()+"#"+d.getMixerFcode());
            map.put("plateNo",d.getPlateNo());
            map.put("mixerFcode",d.getMixerFcode());
            map.put("lineId",d.getLineId());
            /**
             * 获取司机排队位置
             */
            for(Line line : lines) {
                if(d.getLineId().equals(line.getLineId())) {
                    if(line.getProductMixerFcode().contains(d.getMixerFcode())) {
                        map.put("mixerIndex","当前排队:生产中");
                    }
                    if(d.getMixerFcode().equals(line.getReadyMixerFcode())) {
                        map.put("mixerIndex","当前排队:准备中");
                    }
                    if(line.getWaitMixerFcode().contains(d.getMixerFcode())) {
                        map.put("mixerIndex","当前排队:第"+ (line.getWaitMixerFcode().indexOf(d.getMixerFcode())+1) + "位");
                    }
                }
            }
        } else {
            map.put("titleInfo",false);
        }
        return  map;
    }

    @Override
    public List<Mixer> selQaulifiedMixerByDrvId(Integer drvId) {
        return mixerMapper.selQaulifiedMixerByDrvId(drvId);
    }

    @Override
    public Mixer findMixerById(Integer mixerId) {
        return mixerMapper.selectByPrimaryKey(mixerId);
    }


    @Override
    public List<Line> findAllLine() {
        return mixerMapper.selAllLine();
    }


    @Override
    public List<String> findDrvOndutyByDrvId(Integer drvId) {
        return mixerMapper.selDrvOnduty(drvId);
    }

}
