/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.test.core.service;

import com.jkalvered.core.entite.Account;
import com.jkalvered.core.service.NiddahService;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author jonat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jkalvered.test.core.configuration.AppConfigCore.class, com.jkalvered.test.core.configuration.HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class NiddahServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    NiddahService niddahService;

    @Test
    public void testInsert() {
        Account account = new Account();
        account.setLogin(new Date().toString());
        account = niddahService.add(account, account.getClass());
        LOGGER.info(account);
    }

}
