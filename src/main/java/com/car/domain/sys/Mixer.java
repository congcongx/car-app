package com.car.domain.sys;

import java.math.BigDecimal;
import java.util.Date;

public class Mixer {
    private Integer mixerId;

    private String mixerFcode;

    private Integer mixerCcode;

    private String plateNo;

    private BigDecimal curLoad;

    private Short net;

    private Byte bridge;

    private Date regDate;

    private Integer ownerCode;

    private String ownerName;

    private Byte useable;

    private Byte mixerStatus;

    private Integer latelyDlvId;

    public Integer getMixerId() {
        return mixerId;
    }

    public void setMixerId(Integer mixerId) {
        this.mixerId = mixerId;
    }

    public String getMixerFcode() {
        return mixerFcode;
    }

    public void setMixerFcode(String mixerFcode) {
        this.mixerFcode = mixerFcode == null ? null : mixerFcode.trim();
    }

    public Integer getMixerCcode() {
        return mixerCcode;
    }

    public void setMixerCcode(Integer mixerCcode) {
        this.mixerCcode = mixerCcode;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo == null ? null : plateNo.trim();
    }

    public BigDecimal getCurLoad() {
        return curLoad;
    }

    public void setCurLoad(BigDecimal curLoad) {
        this.curLoad = curLoad;
    }

    public Short getNet() {
        return net;
    }

    public void setNet(Short net) {
        this.net = net;
    }

    public Byte getBridge() {
        return bridge;
    }

    public void setBridge(Byte bridge) {
        this.bridge = bridge;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(Integer ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public Byte getUseable() {
        return useable;
    }

    public void setUseable(Byte useable) {
        this.useable = useable;
    }

    public Byte getMixerStatus() {
        return mixerStatus;
    }

    public void setMixerStatus(Byte mixerStatus) {
        this.mixerStatus = mixerStatus;
    }

    public Integer getLatelyDlvId() {
        return latelyDlvId;
    }

    public void setLatelyDlvId(Integer latelyDlvId) {
        this.latelyDlvId = latelyDlvId;
    }
}