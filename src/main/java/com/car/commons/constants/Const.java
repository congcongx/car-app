package com.car.commons.constants;

public interface Const {
    /**
     * 数据源名称
     */
    String DATASOURCE = "dataSource";

    String SESSION_KEY = "session_key:";

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


    String DAY ="DAY";

    String MONTH = "MONTH";

    String YEAR = "YEAR";

    /**
     * 搅拌车状态
     */
    //冻结
    int MIXER_DISABLE = 0;
    //等待派单
    int MIXER_TASKWAIT = 1;
    //等待装料
    int MIXER_LOADWAIT = 2;
    //接料
    int MIXER_LOAD = 3;
    //商砼运输
    int MIXER_DLVTRANS = 4;
    //特殊运输
    int MIXER_SDLVTRANS = 5;
    //休息
    int MIXER_REST = 6;
    //维保
    int MIXER_MEND = 7;

}