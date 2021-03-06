package com.youlishu.DataConfig;
 
import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
 
/**
 * @Description :数据库连接属性配置
 * @Author fengxian
 * @Date  2020/8/19 10:57
 * @Param
 * @return
 **/
@ServletComponentScan
@Configuration
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
 
    @Value("${spring.datasource.primary.url}")
    private String dbUrl1;
 
    @Value("${spring.datasource.primary.username}")
    private String username1;
 
    @Value("${spring.datasource.primary.password}")
    private String password1;

    @Value("${spring.datasource.primary.driver-class-name}")
    private String driverClassName1;



//    @Value("${spring.datasource.secondary.username}")
//    private String username2;
//
//    @Value("${spring.datasource.secondary.password}")
//    private String password2;
//
//    @Value("${spring.datasource.secondary.url}")
//    private String dbUrl2;
//
//    @Value("${spring.datasource.secondary.driver-class-name}")
//    private String driverClassName2;



//    @Value("${spring.datasource.thirdly.username}")
//    private String username3;
//
//    @Value("${spring.datasource.thirdly.password}")
//    private String password3;
//
//    @Value("${spring.datasource.thirdly.url}")
//    private String dbUrl3;
//
//    @Value("${spring.datasource.thirdly.driver-class-name}")
//    private String driverClassName3;



//    @Value("${spring.datasource.orderp.username}")
//    private String username4;
//
//    @Value("${spring.datasource.orderp.password}")
//    private String password4;
//
//    @Value("${spring.datasource.orderp.url}")
//    private String dbUrl4;
//
//    @Value("${spring.datasource.orderp.driver-class-name}")
//    private String driverClassName4;
//
//
//    @Value("${spring.datasource.fifth.username}")
//    private String username5;
//
//    @Value("${spring.datasource.fifth.password}")
//    private String password5;
//
//    @Value("${spring.datasource.fifth.url}")
//    private String dbUrl5;
//
//    @Value("${spring.datasource.fifth.driver-class-name}")
//    private String driverClassName5;




    @Value("5")
    private int initialSize;
 
    @Value("5")
    private int minIdle;
 
    @Value("20")
    private int maxActive;
 
    @Value("10000")
    private int maxWait;
 
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    @Value("18000")
    private int timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    @Value("18000")
    private int minEvictableIdleTimeMillis;
 
    @Value("SELECT 1")
    private String validationQuery;
 
    @Value("true")
    private boolean testWhileIdle;
 
    @Value("false")
    private boolean testOnBorrow;
 
    @Value("false")
    private boolean testOnReturn;
 
    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小
     */
    @Value("true")
    private boolean poolPreparedStatements;
 
    @Value("20")
    private int maxPoolPreparedStatementPerConnectionSize;
    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    @Value("stat,wall,log4j")
    private String filters;
    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     */
    @Value("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500")
    private String connectionProperties;
 
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public DataSource dataSource() {
        return getDruidDataSource(username1, password1, dbUrl1, driverClassName1);
    }
 


//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    //@Primary
//    public DataSource secondSource() {
//        return getDruidDataSource(username2, password2, dbUrl2, driverClassName2);
//    }

//    @Bean(name = "thirdlyDataSource")
//    @Qualifier("thirdlyDataSource")
//    public DataSource thirdlySource() {
//        return getDruidDataSource(username3, password3, dbUrl3, driverClassName3);
//    }

//    @Bean(name = "orderpDataSource")
//    @Qualifier("orderpDataSource")
//    public DataSource orderpSource() {
//        return getDruidDataSource(username4, password4, dbUrl4, driverClassName4);
//    }

//    @Bean(name = "fifthDataSource")
//    @Qualifier("fifthDataSource")
//    public DataSource fifthSource() {
//        return getDruidDataSource(username5, password5, dbUrl5, driverClassName5);
//    }
 
    private DruidDataSource getDruidDataSource(String username, String password, String url, String driverClassName) {
        DruidDataSource datasource = new DruidDataSource();
 
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
 
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {0}", e);
        }
        datasource.setConnectionProperties(connectionProperties);
 
        return datasource;
    }
}