/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller.personne;

import com.niddah.library.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @RequestMapping("/private/index.do")
    public String getSignIn(ModelMap model) {
        AccountDto user = (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("La page privé pour l'utilisateur {}", user.getLogin());
        return "private/index";
    }
}
