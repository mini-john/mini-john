/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.configuration;

import com.jkalvered.web.listener.CustomAuthenticationFailureHandler;
import com.jkalvered.web.listener.CustomAuthenticationProvider;
import com.jkalvered.web.listener.CustomWebAuthenticationDetailsSource;
import com.jkalvered.web.listener.MyLogoutSuccessHandler;
import com.jkalvered.web.listener.MySimpleUrlAuthenticationSuccessHandler;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    private MySimpleUrlAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        // @formatter:off
        http
                .authorizeHttpRequests()
                .requestMatchers("/public/*").anonymous()
                .requestMatchers("/private/**").authenticated()
                .requestMatchers("/private/rav/**").hasRole("Rav")
                .requestMatchers("/admin/**").hasRole("Admin")
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/public/signup.do")
                .failureUrl("/public/signup.do?error=true")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .authenticationDetailsSource(authenticationDetailsSource)
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/invalidSession.do")
                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                .sessionFixation().none()
                .and()
                .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/public/index.do?logSucc=true")
                .deleteCookies("JSESSIONID")
                .permitAll();
        // @formatter:on
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user
                = User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @PostConstruct
    public void postConstruc() {
        LOGGER.info("Security Config - done");
    }

}
