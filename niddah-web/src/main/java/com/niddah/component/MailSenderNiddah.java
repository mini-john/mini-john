/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 *
 * @author Boccara Jonathan
 */
public class MailSenderNiddah {

    @Autowired
    private VelocityEngineFactoryBean velocity;
    @Autowired
    private JavaMailSenderImpl maisSender;

    public VelocityEngineFactoryBean getVelocity() {
        return velocity;
    }

    public void setVelocity(VelocityEngineFactoryBean velocity) {
        this.velocity = velocity;
    }

    public JavaMailSenderImpl getMaisSender() {
        return maisSender;
    }

    public void setMaisSender(JavaMailSenderImpl maisSender) {
        this.maisSender = maisSender;
    }

}
