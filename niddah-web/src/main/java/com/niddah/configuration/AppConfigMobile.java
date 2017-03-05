/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.configuration;

import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Boccara Jonathan
 */
@Configuration
@EnableWebMvc
public class AppConfigMobile extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfigMobile.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Configuration mobile - done ");
    }

    @Bean
    public DeviceResolverHandlerInterceptor
            deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Bean
    public SitePreferenceHandlerInterceptor sitePreferenceHandlerInterceptor() {
        return new SitePreferenceHandlerInterceptor();
    }

    @Bean
    public SitePreferenceHandlerMethodArgumentResolver sitePreferenceHandlerMethodArgumentResolver() {
        return new SitePreferenceHandlerMethodArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sitePreferenceHandlerInterceptor());

        registry.addInterceptor(deviceResolverHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sitePreferenceHandlerMethodArgumentResolver());
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }
    /*
    @Bean
    public LiteDeviceDelegatingViewResolver liteDeviceAwareViewResolver() {

        TilesViewResolver viewResolver = tilesViewResolver();
        //  viewResolver.setOrder(1);
        TilesSupportLiteDeviceDelegatingViewResolver resolver
                = new TilesSupportLiteDeviceDelegatingViewResolver(viewResolver);
        // resolver.setMobilePrefix(".m");
        //resolver.setTabletPrefix(".t");
        resolver.setAttributeName("mDeviceType");
        resolver.setOrder(0);

        resolver.setEnableFallback(false);

        return resolver;
    }

     */
}
