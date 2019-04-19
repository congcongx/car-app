package com.car.domain.sys;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Mixer {
    private Integer mixerId;

    private String mixerFcode;

    private Integer mixerCcode;

    private String plateNo;

    private BigDecimal curLoad;

    private Integer net;

    private Integer bridge;

    private Date regDate;

    private Integer ownerCode;

    private String ownerName;

    private Integer useable;

    private Integer mixerStatus;

    private Integer latelyDlvId;


}