package com.virusvaccine.common.aop;

import com.virusvaccine.common.annotation.SetDataSource;
import com.virusvaccine.common.annotation.SetDataSource.DataSourceType;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.common.utils.RoutingDataSourceManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SetDataSourceAspect {

    @Before("@annotation(com.virusvaccine.common.annotation.SetDataSource) && @annotation(target)")
    public void setDataSource(SetDataSource target) throws NotFoundException {

        if (target.dataSourceType() == DataSourceType.MASTER
                || target.dataSourceType() == DataSourceType.SLAVE
                || target.dataSourceType() == DataSourceType.BOTH) {
            RoutingDataSourceManager.setCurrentDataSourceName(target.dataSourceType());
        } else {
            throw new NotFoundException();
        }

    }
}
