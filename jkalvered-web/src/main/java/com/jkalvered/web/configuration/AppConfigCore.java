/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.configuration;


import com.jkalvered.core.modelmapper.JkalveredModelMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mini-john
 */
@Configuration
@ComponentScan(basePackages = "com.jkalvered.core")
@EnableAsync
public class AppConfigCore {
    
    @Autowired
    private Environment environment;
    
    
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
    
    
//    @Bean
//    public JavaMailSenderImpl javaMailSenderImpl() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        //Set gmail email id
//        mailSender.setUsername(environment.getRequiredProperty("mail.username"));
//        //Set gmail email password
//        mailSender.setPassword(environment.getRequiredProperty("mail.password"));
//        Properties prop = mailSender.getJavaMailProperties();
//        prop.put("mail.transport.protocol", "smtp");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.ssl.trust", "*");
//        
//        prop.put("mail.debug", "true");
//        mailSender.setJavaMailProperties(prop);
//        return mailSender;
//    }

//    @Bean(name = "velocity")
//    public VelocityEngineFactoryBean velocity() {
//        VelocityEngineFactoryBean velocity = new VelocityEngineFactoryBean();
//        velocity.setResourceLoaderPath("classpath:template");
//        velocity.setPreferFileSystemAccess(false);
//        return velocity;
//    }
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:template");
        return bean;
    }
    
//    @Bean
//    public MailSenderNiddah maisSender() {
//        return new MailSenderNiddah();
//    }
    
//    @Bean
//    public CaptchaSettings captchaSettings() {
//        CaptchaSettings captchaSettings = new CaptchaSettings();
//        captchaSettings.setSite(environment.getProperty("google.recaptcha.key.site"));
//        captchaSettings.setSecret(environment.getProperty("google.recaptcha.key.secret"));
//        
//        return captchaSettings;
//    }
    
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3 * 1000);
        factory.setReadTimeout(7 * 1000);
        return factory;
    }
    
    @Bean
    public RestOperations restTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.clientHttpRequestFactory());
        return restTemplate;
    }
    
//    @Bean
//    public ActiveUserStore activeUserStore() {
//        return new ActiveUserStore();
//    }
//    
//    @Bean("adminDto")
//    public AccountDto getAdmin() {
//        AccountDto adminDto = new AccountDto();
//        adminDto.setId(1L);
//        adminDto.setLogin(environment.getProperty("admin.login"));
//        adminDto.setPassword(environment.getProperty("admin.password"));
//        adminDto.setEtatAccount(EtatAccount.actif);
//        adminDto.setAccountBlock(false);
//        adminDto.setMail(environment.getProperty("admin.mail"));
//        PersonneDto personneDto = new PersonneDto();
//        personneDto.setAccount(adminDto);
//        personneDto.setId(1L);
//        personneDto.setNom(environment.getProperty("admin.nom"));
//        personneDto.setPrenom(environment.getProperty("admin.prenom"));
//        adminDto.setPersonne(personneDto);
//        personneDto.setAccount(adminDto);
//        return adminDto;
//        
//    }

//    @Bean 
//    public MonitoringSpringAdvisor javaMelody() throws ClassNotFoundException{
//        MonitoringSpringAdvisor monit= new MonitoringSpringAdvisor();
//        MonitoredWithInterfacePointcut mo=new MonitoredWithInterfacePointcut();
//        mo.setInterfaceName("com.niddah.*");
//        monit.setPointcut(new MonitoredWithInterfacePointcut());
//        return monit;
//    }
}
