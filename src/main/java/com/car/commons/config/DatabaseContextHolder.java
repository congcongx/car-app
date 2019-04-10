package com.car.commons.config;

import com.car.commons.enums.DatabaseType;

public class DatabaseContextHolder {

    private static ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    public static void setDatabaseType(DatabaseType type) {
        DatabaseContextHolder.contextHolder.set(type);
    }
}
