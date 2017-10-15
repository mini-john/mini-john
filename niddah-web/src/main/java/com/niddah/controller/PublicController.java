/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.niddah.core.service.BlogService;
import com.niddah.core.service.NewsletterService;
import com.niddah.json.JsonReturn;
import java.sql.SQLIntegrityConstraintViolationException;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mini-john
 */
@Controller
public class PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    BlogService blogService;
    @Autowired
    NewsletterService newsletterService;

    @RequestMapping(value = {"/public/index.do", "/", "/public/"})
    public String index(ModelMap model) {
        LOGGER.info("La page public.index est demandé");
        model.addAttribute("post", blogService.getFirstPost());
        model.addAttribute("comments", blogService.getLastFourComment());

        return "public/index";
    }

    @RequestMapping(value = "/public/newsletter.do", method = RequestMethod.POST)
    public @ResponseBody
    JsonReturn inscription(@RequestParam("email") String email) {
        JsonReturn response = new JsonReturn();
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid) {
            response.setCode("205");
            response.setMsg("Email invalide");
            return response;
        }
        try {
            newsletterService.addEmail(email);
            response.setCode("200");
            response.setMsg("Email Rajouté");
        } catch (org.hibernate.exception.ConstraintViolationException ex) {
            LOGGER.error("Erreur d'ajout de newsletter{}", email, ex);
            if (ex.getCause() instanceof MySQLIntegrityConstraintViolationException) {

                MySQLIntegrityConstraintViolationException cdts = (MySQLIntegrityConstraintViolationException) ex.getCause();
                if (cdts.getMessage().contains("emailExist")) {
                    response.setCode("204");
                    response.setMsg("Email déja présent");
                } else {
                    response.setCode("500");
                    response.setMsg("Erreur, merci de prendre contact");
                }

            }

        }
        return response;
    }
    @RequestMapping(value = {"/public/delete_newsletter.do"})
    public String deleteNewsletter(ModelMap model,@RequestParam("email") String email) {
       String reponse= newsletterService.deactivateMail(email);
        return "public/newsletterdeleted";
        
    }
}
