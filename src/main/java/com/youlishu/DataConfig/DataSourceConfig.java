package com.youlishu.DataConfig;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSource secondDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean(name = "thirdlyDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.thirdly")
//    public DataSource thirdlyDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean(name = "orderpDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.orderp")
//    public DataSource orderpDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean(name = "fifthDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.fifth")
//    public DataSource fifthDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name="dynamicDataSource")
    @Primary    //优先使用，多数据源
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource master = primaryDataSource();
//        DataSource slave1 = secondDataSource();
//        DataSource slave2 = thirdlyDataSource();
//        DataSource slave3 = orderpDataSource();
//        DataSource slave4 = fifthDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master);
        //配置多数据源
        Map<Object,Object> map = new HashMap<>();
        map.put(DataSourceType.LOT.getName(), master);	//key需要跟ThreadLocal中的值对应
//        map.put(DataSourceType.STHJJ.getName(), slave1);
//        map.put(DataSourceType.STHJJ.getName(), slave2);
//        map.put(DataSourceType.ORDERP.getName(), slave3);
//        map.put(DataSourceType.SJJK.getName(), slave4);
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }
}
