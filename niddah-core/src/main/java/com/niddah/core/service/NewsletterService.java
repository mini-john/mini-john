/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;

import com.niddah.core.entity.Newsletter;
import com.niddah.core.repository.NewsletterRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Boccara Jonathan
 */
@Service
@Transactional
public class NewsletterService extends CrudService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    public void addEmail(String email) {
        Newsletter nwl = new Newsletter();
        nwl.setActif(true);
        nwl.setDateInscription(new Date());
        nwl.setEmail(email);
        newsletterRepository.persist(nwl);
    }

    public String deactivateMail(String email) {
        String reponse = "";
        Newsletter newsletter = newsletterRepository.emailExist(email);
        if (newsletter == null) {
            return "Email Inexistant";
        } else {
            newsletter.setActif(false);
            newsletter.setDateSuppresion(new Date());
            newsletterRepository.update(newsletter);
            return "Email supprimé";
        }

    }

}
