package com.car.commons.config;

import com.car.commons.enums.DataSourceEnum;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceEnum(DataSourceEnum dataSourceEnum) {
        CONTEXT_HOLDER.set(dataSourceEnum);
    }

    public static DataSourceEnum getDataSourceEnum() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceEnum() {
        CONTEXT_HOLDER.remove();
    }

}
