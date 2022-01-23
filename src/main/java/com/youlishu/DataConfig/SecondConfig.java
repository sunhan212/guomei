//package com.lot.DataConfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactorySecond",
//        transactionManagerRef = "transactionManagerSecond",
//        basePackages = {"com.lot.dao.shengtai"}) //设置Repository所在位置
//public class SecondConfig {
//    @Autowired
//    @Qualifier("secondaryDataSource")
//    private DataSource secondDataSource;
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Bean(name = "entityManagerSecond")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactorySecond(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "entityManagerFactorySecond")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecond(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(secondDataSource)
//                .properties(getVendorProperties(secondDataSource))
//                .packages("com.ywtg.bean.shengtai") //设置实体类所在位置
//                .persistenceUnit("secondaryPersistenceUnit")
//                .build();
//    }
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerSecond")
//    public PlatformTransactionManager transactionManagerSecond(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactorySecond(builder).getObject());
//    }
//}