package com.car.domain.sys;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Line {

    private Integer lineId;

    private String lineName;

    private List<String> productMixerFcode = new ArrayList<>();

    private String readyMixerFcode;

    private List<String> waitMixerFcode = new ArrayList<>();
}
