/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller.admin;

import com.niddah.core.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Boccara Jonathan
 */
@Controller
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("admin/index.do")
    public String index(ModelMap m) {
        Account user = (Account)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("La page index admin est demandée {}", user.getLogin());
        m.addAttribute("account", user);
        return "admin/index";
    }
}
