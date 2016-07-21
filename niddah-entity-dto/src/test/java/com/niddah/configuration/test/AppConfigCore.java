/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.configuration.test;

import java.io.FileNotFoundException;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author mini-john
 */
@Configuration
@ComponentScan(basePackages = "com.niddah.core"/*,
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})*/)

public class AppConfigCore {

    @Bean
    Mapper newMapper() {
        return new DozerBeanMapper();
    }
    @Bean
    CastorMarshaller castorMarshaller() throws FileNotFoundException{
        CastorMarshaller cM= new CastorMarshaller();
        Resource resource = new ClassPathResource("mapping.xml");
        cM.setMappingLocation(resource);
        return cM;
    }

}
