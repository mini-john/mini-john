/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.component;

import com.niddah.library.dto.PersonneDto;
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
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 *
 * @author Boccara Jonathan
 */
public class MailSenderNiddah {

    @Autowired()
    @Qualifier(value = "velocity")
    private VelocityEngine velocity;
    @Autowired
    private JavaMailSenderImpl maisSender;

    public VelocityEngine getVelocity() {
        return velocity;
    }

    public void setVelocity(VelocityEngine velocity) {
        this.velocity = velocity;
    }

    public JavaMailSenderImpl getMaisSender() {
        return maisSender;
    }

    public void setMaisSender(JavaMailSenderImpl maisSender) {
        this.maisSender = maisSender;
    }

    @Async
    public void sendMailActivationAccount(final PersonneDto personneDto) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mm) throws Exception {

                MimeMessageHelper message = new MimeMessageHelper(mm);
                message.setTo(personneDto.getAccount().getMail());
                message.setBcc("adrianmatei@gmail.com");
                message.setFrom(new InternetAddress("michel.jettest@gmaioll.com"));
                message.setSubject("Creation du compte sur jKalVered");
                message.setSentDate(new Date());
                Map model = new HashMap();
                model.put("personneDto", personneDto);

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocity, "/emailActivation.html", "UTF-8", model);
                message.setText(text, true);
            }
        };
        maisSender.send(preparator);

    }

}
