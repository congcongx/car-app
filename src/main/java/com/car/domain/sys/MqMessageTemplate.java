package com.car.domain.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信公众号司机消息模板
 */
public class MqMessageTemplate implements Serializable {

    //司机唯一凭证
    private String openid;
    //生产线
    private String lineName;
    //发货单编号
    private Integer dlvId;
    //消息内容
    private String message;
    //搅拌车厂码
    private String mixerFcode;
    //工厂简称
    private String facShort;
    //时间
    private Date  time;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getDlvId() {
        return dlvId;
    }

    public void setDlvId(Integer dlvId) {
        this.dlvId = dlvId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMixerFcode() {
        return mixerFcode;
    }

    public void setMixerFcode(String mixerFcode) {
        this.mixerFcode = mixerFcode;
    }

    public String getFacShort() {
        return facShort;
    }

    public void setFacShort(String facShort) {
        this.facShort = facShort;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MqMessageTemplate{" +
                "openid='" + openid + '\'' +
                ", lineName='" + lineName + '\'' +
                ", dlvId=" + dlvId +
                ", message='" + message + '\'' +
                ", mixerFcode='" + mixerFcode + '\'' +
                ", facShort='" + facShort + '\'' +
                ", time=" + time +
                '}';
    }
}
