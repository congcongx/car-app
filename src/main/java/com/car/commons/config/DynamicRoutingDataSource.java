package com.car.commons.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        logger.debug("Current DataSource is [{}]", DataSourceContextHolder.getDataSourceKey());
        return DataSourceContextHolder.getDataSourceKey();
    }
}
