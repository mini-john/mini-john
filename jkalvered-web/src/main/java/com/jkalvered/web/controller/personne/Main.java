/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.controller.personne;


import com.jkalvered.core.dto.AccountDto;
import org.apache.logging.log4j.LogManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boccara Jonathan
 */
@Controller
public class Main {

private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/private/index.do")
    public String getSignIn(ModelMap model) {
        AccountDto user = (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("La page privé pour l'utilisateur {}", user.getLogin());
        return "private/index";
    }
}
