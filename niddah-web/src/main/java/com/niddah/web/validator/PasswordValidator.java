/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.web.validator;

import com.niddah.library.dto.PersonneDto;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Boccara Jonathan
 */
public class PasswordValidator implements Validator {

    MessageSource messageSource;

    public PasswordValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> type) {
        return PersonneDto.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonneDto personneDto = (PersonneDto) o;
        String password = personneDto.getAccount().getPassword();
        String confPassword = personneDto.getAccount().getConfirmation();
        if (password.isEmpty()) {
            errors.rejectValue("account.password", "account.password.error.null", messageSource.getMessage("account.password.error.null", new String[]{}, new Locale("fr")));

        }
        if (confPassword.isEmpty()) {
            errors.rejectValue("account.confirmation", "account.password.error.confirmation.null", messageSource.getMessage("account.password.error.confirmation.null", new String[]{}, new Locale("fr")));

        }
        if (!password.equals(confPassword)) {
            errors.rejectValue("account.password", "account.password.error.match", messageSource.getMessage("account.password.error.match", new String[]{}, new Locale("fr")));
            errors.rejectValue("account.confirmation", "account.password.error.match", messageSource.getMessage("account.password.error.match", new String[]{}, new Locale("fr")));

        }

    }

}
