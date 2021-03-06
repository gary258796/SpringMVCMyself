package com.gary.config;


import com.gary.aop.After_updateUserInfo_Img;
import com.gary.aop.Before_Home;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.gary")
@PropertySource({"classpath:/mysql.properties"})
@EnableJpaRepositories(basePackages = "com.gary.persistence.dao")
public class WebConfig implements WebMvcConfigurer {

    // hold jdbc properties
    @Autowired
    private Environment env ;

    private Logger logger = Logger.getLogger(getClass().getName()) ;

    @Bean
    public Before_Home before_home() {
        return new Before_Home();
    }

    @Bean
    public After_updateUserInfo_Img after_updateUserInfo_img() {
        return new After_updateUserInfo_Img();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver() ;
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        // resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource() ;

        // set jdbc driver
        try {
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e) ;
        }

        // log the connection props
        logger.info(">>> jdbc.url= " + env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user= " + env.getProperty("jdbc.user"));

        // set jdbc conn props
        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        securityDataSource.setInitialPoolSize( getIntPropertyfromString("connection.pool.initialPoolSize") );
        securityDataSource.setMinPoolSize( getIntPropertyfromString("connection.pool.minPoolSize") );
        securityDataSource.setMaxPoolSize( getIntPropertyfromString("connection.pool.maxPoolSize") );
        securityDataSource.setMaxIdleTime( getIntPropertyfromString("connection.pool.maxIdleTime") );

        return securityDataSource ;
    }


    private int getIntPropertyfromString(String propName) {

        String propVal = env.getProperty(propName) ;

        int returnInt = Integer.parseInt(propVal) ;

        return returnInt ;
    }

    private Properties getHibernateProperties() {

        Properties props = new Properties();

        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect")) ;
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql")) ;
//        props.setProperty("hibernate.hbm2ddl.auto", "create-drop") ;

        return props ;

    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        // create session factory
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.gary" }); //.persistence.entity
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }


    // 叫 transactionManager, 當有需要用到transactionManager會默認使用名稱為transactionManager的transactionManager
    // 如果有其他名稱的transactionManager, 例如JpaTransactionManager
    // 可以在使用之前 用@Transactional(transactionManager = "例如JpaTransactionManager")
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    @Autowired
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {

        HibernateTemplate hibernateTemplate = new HibernateTemplate() ;

        hibernateTemplate.setSessionFactory( sessionFactory );

        return  hibernateTemplate ;
    }

    // 上傳圖片相關設定
    @Bean(name="multipartResolver")
    public CommonsMultipartResolver multipartResolver() throws IOException {

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver() ;

        commonsMultipartResolver.setMaxUploadSize(100000000);
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        return commonsMultipartResolver ;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 處理靜態資源對應路徑（如圖片）
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("/WEB-INF/upload/")
                .setCachePeriod(31556926);
    }

}