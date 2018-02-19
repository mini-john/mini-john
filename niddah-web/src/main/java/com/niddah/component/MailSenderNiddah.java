/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.component;

import freemarker.template.Configuration;

import com.niddah.library.dto.PersonneDto;
import freemarker.template.Template;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Boccara Jonathan
 */
public class MailSenderNiddah {

   
    
    @Autowired
    private Configuration freemarkerConfig;
    @Autowired
    private JavaMailSenderImpl maisSender;

   

    public JavaMailSenderImpl getMaisSender() {
        return maisSender;
    }

    public void setMaisSender(JavaMailSenderImpl maisSender) {
        this.maisSender = maisSender;
    }

    @Async
    public void sendMailActivationAccount(final PersonneDto personneDto) {
        MimeMessagePreparator preparator = (MimeMessage mm) -> {
            MimeMessageHelper message = new MimeMessageHelper(mm);
            message.setTo(personneDto.getAccount().getMail());
            message.setFrom(new InternetAddress("michel.jettest@gmaioll.com"));
            message.setSubject("Creation du compte sur jKalVered");
            message.setSentDate(new Date());
            Map model = new HashMap();
            model.put("personneDto", personneDto);
            Template t = freemarkerConfig.getTemplate("/emailActivation.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            message.setText(text, true);
        };
        maisSender.send(preparator);

    }

    @Async
    public void sendMailCompteCree(PersonneDto personneDto, String password) {
        MimeMessagePreparator preparator = (MimeMessage mm) -> {
            MimeMessageHelper message = new MimeMessageHelper(mm);
            message.setTo(personneDto.getAccount().getMail());
            message.setFrom(new InternetAddress("michel.jettest@gmaioll.com"));
            message.setSubject("Creation du compte sur jKalVered");
            message.setSentDate(new Date());
            Map model = new HashMap();
            model.put("personneDto", personneDto);
            model.put("password", password);

            Template t = freemarkerConfig.getTemplate("/emailCreation.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            message.setText(text, true);
        };
        maisSender.send(preparator);
    }

    public void sendMailResetPassword(PersonneDto personneDto) {
        MimeMessagePreparator preparator = (MimeMessage mm) -> {
            MimeMessageHelper message = new MimeMessageHelper(mm);
            message.setTo(personneDto.getAccount().getMail());
            message.setFrom(new InternetAddress("michel.jettest@gmaioll.com"));
            message.setSubject("Réinitialisation du mot de passe sur jKalVered");
            message.setSentDate(new Date());
            Map model = new HashMap();
            model.put("personneDto", personneDto);

           
            Template t = freemarkerConfig.getTemplate("/emailVerificationResetPassword.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            message.setText(text, true);
        };
        maisSender.send(preparator);
    }

    public void sendMailPasswordChange(PersonneDto personneDto, String password) {
        MimeMessagePreparator preparator = (MimeMessage mm) -> {
            MimeMessageHelper message = new MimeMessageHelper(mm);
            message.setTo(personneDto.getAccount().getMail());
            message.setFrom(new InternetAddress("michel.jettest@gmaioll.com"));
            message.setSubject("Creation du compte sur jKalVered");
            message.setSentDate(new Date());
            Map model = new HashMap();
            model.put("personneDto", personneDto);
            model.put("password", password);

          
            Template t = freemarkerConfig.getTemplate("/emailChangePassword.html");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
           
            message.setText(text, true);
        };
        maisSender.send(preparator);
    }

}
