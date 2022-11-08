package com.jkalvered.test.core.configuration;

import java.util.Properties;

import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.jkalvered.test.core.configuration"})
@PropertySources({
    @PropertySource(value = "classpath:application.properties"),
    @PropertySource(value = "classpath:application-testing.properties", ignoreResourceNotFound = false)
})
public class HibernateConfiguration {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean dataSourceProd() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceProdJkalVered());
        sessionFactory.setPackagesToScan(new String[]{"com.jkalvered.core.entite"});
        sessionFactory.setHibernateProperties(hibernatePropertiesProdJkalVered());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSourceProdJkalVered() {
        LOGGER.info("Login db {} password {}", environment.getRequiredProperty("jdbc.username"), environment.getRequiredProperty("jdbc.password"));

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        System.setProperty("hsqldb.method_class_names", "com.jkalvered.core.entite.*");
        LOGGER.info("url database"+ dataSource.getUrl());
        return dataSource;
    }

    private Properties hibernatePropertiesProdJkalVered() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
 //      properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hsqldb.lock_file", false);
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
