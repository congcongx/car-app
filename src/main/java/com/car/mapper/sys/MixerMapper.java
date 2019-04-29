package com.car.mapper.sys;

import com.car.domain.sys.Dlv;
import com.car.domain.sys.Line;
import com.car.domain.sys.Mixer;

import java.util.List;

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

    List<Line> selAllLine();

    /**
     * 查询生产线上的车辆
     * @return
     */
    List<Dlv> selLineMixer();

    /**
     * 查询搅拌车所在生产线
     * @param mixerFcode
     * @return
     */
    Integer selLineByMixerFcode(String mixerFcode);

    /**
     *  根据司机云码查询发货单
     * @param drvCcode
     * @return
     */
    Dlv selDlvByDrvCcodeAndMixerFcode(Integer drvCcode,String mixerFcode);

    /**
     * 根据司机查询可以驾驶的搅拌车
     * @param drvId
     * @return
     */
    List<Mixer> selQaulifiedMixerByDrvId(Integer drvId);
}