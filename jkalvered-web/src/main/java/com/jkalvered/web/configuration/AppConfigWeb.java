package com.jkalvered.web.configuration;

import com.jkalvered.core.dto.AccountDto;
import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.service.PersonneService;
import com.jkalvered.library.enumeration.EtatAccount;
import com.jkalvered.library.enumeration.Origine;
import com.jkalvered.library.enumeration.RoleUser;
import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.enumeration.TypeCycle;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jkalvered.web")
public class AppConfigWeb implements WebMvcConfigurer {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/messages");
        messageSource.setDefaultEncoding("UTF-8");
        // messageSource.setDefaultLocale(Locale.FRANCE);
        return messageSource;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * Configure TilesConfigurer.
     *
     * @return
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/views/**/tiles.xml", "/WEB-INF/views/**/tiles_fr.xml", "/WEB-INF/views/**/tiles_en.xml"});
        tilesConfigurer.setCheckRefresh(true);
        tilesConfigurer.setPreparerFactoryClass(org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory.class);
        return tilesConfigurer;
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript
     * etc...
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/fr/**").addResourceLocations("/fr/");

    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("jkalvered_lang.LOCALE");
        cookieLocaleResolver.setCookiePath("/jkalvered-web");
        cookieLocaleResolver.setDefaultLocale(Locale.FRANCE);
        return cookieLocaleResolver;
    }

    @Autowired
    private Environment environment;

    @Bean("adminDto")
    public AccountDto getAdmin() {
        AccountDto adminDto = new AccountDto();
        adminDto.setId(1L);
        adminDto.setLogin(environment.getProperty("admin.login"));
        adminDto.setPassword(environment.getProperty("admin.password"));
        adminDto.setEtatAccount(EtatAccount.actif);
        adminDto.setAccountBlock(false);
        adminDto.setMail(environment.getProperty("admin.mail"));
        PersonneDto personneDto = new PersonneDto();
        personneDto.setAccount(adminDto);
        personneDto.setId(1L);
        personneDto.setNom(environment.getProperty("admin.nom"));
        personneDto.setPrenom(environment.getProperty("admin.prenom"));
        adminDto.setPersonne(personneDto);
        personneDto.setAccount(adminDto);
        return adminDto;

    }
    @Autowired

    private PersonneService personneService;

    @Profile("dev")

    public void initProd() {
        LOGGER.info("Configuation web - done");
    }

    @Profile("dev")
    @PostConstruct
    public void initDev() {
        LOGGER.info("Configuation web - done");
        LOGGER.info("Rajout de l'utilisateur test");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        Account account = new Account();
        account.setLogin("login");
        account.setMail("jonathan.boccara@gmail.com");
        account.setPassword(passwordEncoder.encode("azerty"));
        account.setRoleUser(RoleUser.Femme);

        Personne personne = new Personne();
        personne.setNom("Boccara");
        personne.setPrenom("Virginie");
        personne.setSexe(Sexe.Femme);
        com.jkalvered.core.entite.Configuration configuration = new com.jkalvered.core.entite.Configuration();
        configuration.setPersonne(personne);
        configuration.setOrigine(Origine.Sefarade);
        configuration.setElevation(elevation);
        configuration.setLatitude(latitude);
        configuration.setLocationName(locationName);
        configuration.setLongitude(longitude);
        configuration.setTimeZone("Europe/Monaco");
        configuration.setDoMohDahouk(true);
        configuration.setPrichaHoutChani(true);
        configuration.setPrichaBenonitHovotDaat(true);
        configuration.setPrihaHovotYair(true);
        configuration.setPrichaOrZaroua(true);

        personne.setConfiguration(configuration);
        personne.setAccount(account);
        account.setPersonne(personne);
        personne.setTypeCycle(TypeCycle.LoKavoua);
        Personne personneRes = personneService.add(personne, personne.getClass());
        LOGGER.info(personneRes.toString());

    }
}
