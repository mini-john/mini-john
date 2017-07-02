/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller.admin;

import com.niddah.core.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boccara Jonathan
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping("/admin/utilisateur/index.do")
    public String index(ModelMap model, Integer offset, Integer maxResults) {
        model.addAttribute("accounts", accountService.allUserWithPagination( offset, (maxResults == null) ? 5 : maxResults));
        model.addAttribute("count", accountService.countUser());
        model.addAttribute("offset", offset);

        return "admin/utilisateur/index";
    }

}
