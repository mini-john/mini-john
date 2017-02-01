/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.constraint;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mini-john
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy =Mail.MailValidator.class)
public @interface  Mail {
    static final Logger logger = LoggerFactory.getLogger(Mail.class);

    String message() default "{com.niddah.constraint.mail.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class MailValidator implements ConstraintValidator<Mail, String> {

        Pattern p = Pattern.compile("");//TODO mettre regex pour mail

        @Override
        public void initialize(final Mail hasId) {
        }

        @Override
        public boolean isValid(final String numSecu, final ConstraintValidatorContext constraintValidatorContext) {
            if(numSecu==null) return true;
            return (numSecu.isEmpty()?true : p.matcher(numSecu).find());
        }

    }
}
