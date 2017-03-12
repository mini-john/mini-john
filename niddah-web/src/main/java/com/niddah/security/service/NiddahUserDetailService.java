/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.security.service;

import com.niddah.configuration.SecurityConfiguration;
import com.niddah.core.entity.Account;
import com.niddah.core.service.AccountService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class NiddahUserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NiddahUserDetailService.class);

    @Autowired
    private AccountService userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        Account user = userService.findByUserName(userName);
        LOGGER.info("User : " + user);
        if (user == null) {
            LOGGER.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                !user.getAccountBlock(), true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Account user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));

        LOGGER.info("authorities :" + authorities);
        return authorities;
    }

}
