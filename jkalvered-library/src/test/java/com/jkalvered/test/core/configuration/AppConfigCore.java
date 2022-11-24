/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.test.core.configuration;

import com.jkalvered.core.modelmapper.JkalveredModelMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author mini-john
 */
@Configuration
@ComponentScan(basePackages = "com.jkalvered.core"/*,
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})*/)

public class AppConfigCore {

    @Bean
    public JkalveredModelMapper mapper() {
        JkalveredModelMapper mapper = new JkalveredModelMapper();
        ModelMapper modelMap = new ModelMapper();
        modelMap.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setUseOSGiClassLoaderBridging(true)
                .setPreferNestedProperties(false)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
        mapper.setCastorMarshaller(modelMap);
        return mapper;
    }

}
