/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.configuration;

import java.io.FileNotFoundException;
import java.util.Properties;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 *
 * @author mini-john
 */
@Configuration
@ComponentScan(basePackages = "com.niddah.core"/*,
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})*/)

public class AppConfigCore {

    @Autowired
    private Environment environment;

    @Bean
    Mapper newMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    CastorMarshaller castorMarshaller() throws FileNotFoundException {
        CastorMarshaller cM = new CastorMarshaller();
        Resource resource = new ClassPathResource("mapping.xml");
        cM.setMappingLocation(resource);
        return cM;
    }

    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        //Set gmail email id
        mailSender.setUsername(environment.getRequiredProperty("mail.username"));
        //Set gmail email password
        mailSender.setPassword(environment.getRequiredProperty("mail.password"));
        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    public VelocityEngineFactoryBean velocity() {
        VelocityEngineFactoryBean velocity = new VelocityEngineFactoryBean();
        velocity.setResourceLoaderPath("classpath:template");
        return velocity;
    }

}
