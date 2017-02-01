/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.core.entity.Account;
import com.niddah.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mini-john
 */
@Controller
public class PublicController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value={"/public/index.do","/","/public/"})
    public ModelAndView index() {
        Account account = new Account();
        account.setLogin("test");
       // accountService.AddAcount(account);
        return new ModelAndView("public/index");
    }

}
