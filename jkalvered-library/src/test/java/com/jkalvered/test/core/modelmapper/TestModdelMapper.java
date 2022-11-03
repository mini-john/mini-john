package com.jkalvered.test.core.modelmapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jkalvered.core.dto.AccountDto;
import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.modelmapper.JkalveredModelMapper;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author mini-john
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jkalvered.test.core.configuration.AppConfigCore.class, com.jkalvered.test.core.configuration.HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class TestModdelMapper {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    @Autowired
    JkalveredModelMapper jkalveredModelMapper;

    public TestModdelMapper() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCastor() throws NoSuchMethodException {
        Account account = new Account();
        account.setId(1L);
        account.setMail("jonathan.boccara@gmail.com");
        Personne personne = new Personne();
        personne.setNom("boccara");
        account.setPersonne(personne);
        AccountDto accountDto = jkalveredModelMapper.convert(account, AccountDto.class);

        LOGGER.info(account.toString());
        LOGGER.info(accountDto.toString());

    }
}
