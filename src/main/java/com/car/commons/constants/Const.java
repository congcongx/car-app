package com.car.commons.constants;

public interface Const {
    /**
     * 数据源名称
     */
    String DATASOURCE = "dataSource";

    String SESSION_KEY = "session_key";

    Integer USEABLE_ACTIVE = 1; //激活

    Integer USEABLE_FREEZE = 0; // 冻结

    /**
     * 发货单生产状态
     */
    Integer DLV_START = 3; //开始生产

    /**
     * 微信公众账好ACCESS_TOKEN
     */
    String WX_ACCESS_TOKEN_KEY = "WX:ACCESS:TOKEN:KEY";

}