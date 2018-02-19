package com.niddah.controller.listener;

import com.niddah.core.entity.Account;
import com.niddah.core.service.AccountService;
import com.niddah.library.dto.AccountDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private AccountService userRepository;
    @Autowired()
    @Qualifier(value = "adminDto")
    private AccountDto adminDto;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
         AccountDto user = userRepository.findByUserName(auth.getName());
        if (StringUtils.equals(auth.getName(), adminDto.getLogin())) {
            user = adminDto;
        }
        if ((user == null)) {
            throw new BadCredentialsException("Invalid username or password");
        }

        final Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
