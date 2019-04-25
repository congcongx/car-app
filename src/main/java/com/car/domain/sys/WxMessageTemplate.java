package com.car.domain.sys;

import lombok.Data;

import java.util.Map;

@Data
public class WxMessageTemplate {

    private String touser;

    private String template_id;

    Map<String,Map<String,Object>> data;
}
