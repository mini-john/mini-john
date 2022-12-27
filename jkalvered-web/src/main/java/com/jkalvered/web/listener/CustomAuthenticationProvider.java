package com.jkalvered.web.listener;

//@Component
import com.jkalvered.core.dto.AccountDto;
import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.service.PersonneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private PersonneService userRepository;
    @Autowired()
    @Qualifier(value = "adminDto")
    private AccountDto adminDto;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        PersonneDto personne = userRepository.getPersonneByLogin(auth.getName());
        AccountDto user = null;
        if (personne != null) {
            user = personne.getAccount();
        }

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
