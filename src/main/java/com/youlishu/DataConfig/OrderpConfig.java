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
//        entityManagerFactoryRef = "entityManagerFactoryOrderp",
//        transactionManagerRef = "transactionManagerOrderp",
//        basePackages = {"com.lot.dao.orderp"}) //设置Repository所在位置
//public class OrderpConfig {
//    @Autowired
//    @Qualifier("orderpDataSource")
//    private DataSource orderpDataSource;
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Bean(name = "entityManagerOrderp")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryOrderp(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "entityManagerFactoryOrderp")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOrderp(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(orderpDataSource)
//                .properties(getVendorProperties(orderpDataSource))
//                .packages("com.ywtg.bean.orderp") //设置实体类所在位置
//                .persistenceUnit("orderpPersistenceUnit")
//                .build();
//    }
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerOrderp")
//    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryOrderp(builder).getObject());
//    }
//}