package com.gary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackages = "com.gary.persistence.dao")
@EnableTransactionManagement
@PropertySource({"classpath:/mysql.properties"})
public class JpaConfig { // for jpa repository

    @Autowired
    private DataSource dataSource ;

    // hold jdbc properties
    @Autowired
    private Environment env ;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){

        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        emfb.setDataSource(dataSource);
        emfb.setJpaProperties(getHibernateProperties());
        emfb.setPackagesToScan("com.gary.persistence.entity");
        emfb.setJpaVendorAdapter(jpaVendorAdapter());

        return emfb;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor() ;
    }


    private Properties getHibernateProperties() {

        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect")) ;
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql")) ;
//        props.setProperty("hibernate.hbm2ddl.auto", "create-drop") ;

        return props ;

    }

}
