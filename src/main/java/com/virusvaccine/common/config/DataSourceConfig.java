package com.virusvaccine.common.config;

import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;
import com.virusvaccine.common.utils.RoutingDataSourceManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource(
            @Qualifier(value = "masterDataSource") DataSource masterDataSource,
            @Qualifier(value = "slaveDataSource") DataSource slaveDataSource) {

        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                DataSourceType dataSourceType = RoutingDataSourceManager.getCurrentDataSourceName();

                if (TransactionSynchronizationManager
                        .isActualTransactionActive()) {
                    boolean readOnly = TransactionSynchronizationManager
                            .isCurrentTransactionReadOnly();
                    if (readOnly) {
                        dataSourceType = DataSourceType.SLAVE;
                    } else {
                        dataSourceType = DataSourceType.MASTER;
                    }
                } else if (dataSourceType == DataSourceType.BOTH){
                    Random r = new Random();
                    int i = r.nextInt(2);
                    if(i == 1) {
                        dataSourceType = DataSourceType.MASTER;
                    } else {
                        dataSourceType = DataSourceType.SLAVE;
                    }
                }

                RoutingDataSourceManager.removeCurrentDataSourceName();
                return dataSourceType;
            }
        };

        Map<Object, Object> targetDataSources = new HashMap<>();

        targetDataSources.put(DataSourceType.MASTER, masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE, slaveDataSource);

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }
}
