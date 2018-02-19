/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.security.service;

import com.niddah.core.entity.Account;
import com.niddah.core.repository.AccountRepository;
import com.niddah.library.dto.AdminDto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("customUserDetailsService")
@Transactional(noRollbackFor = RuntimeException.class)
public class NiddahUserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NiddahUserDetailService.class);

    @Autowired
    private AccountRepository userService;
    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AdminDto adminDto;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        Account user = userService.findByUserName(userName);
        LOGGER.info("User : " + user);
        final String ip = getClientIP();
        LOGGER.info("L'addresse ip est : " + ip);
        if (loginAttemptService.isBlockedUser(ip, user)) {
            user.setAccountBlock(Boolean.TRUE);
            userService.update(user);
            throw new RuntimeException("blocked");
        }
        if (StringUtils.equals(userName, adminDto.getLogin())) {
            return new org.springframework.security.core.userdetails.User(adminDto.getLogin(), passwordEncoder.encode(adminDto.getPassword()),
                    !adminDto.getAccountBlock(), true, true, true, getGrantedAuthorities(adminDto));
        }
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

    private List<GrantedAuthority> getGrantedAuthorities(AdminDto user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_Admin"));

        LOGGER.info("authorities :" + authorities);
        return authorities;
    }

    private String getClientIP() {
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();

        ServletRequestAttributes att = (ServletRequestAttributes) attribs;
        final String xfHeader = att.getRequest().getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return att.getRequest().getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
