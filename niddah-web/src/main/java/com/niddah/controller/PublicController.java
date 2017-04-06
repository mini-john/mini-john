/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.core.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mini-john
 */
@Controller
public class PublicController {

    private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

    
    @Autowired
    BlogService blogService;

    @RequestMapping(value = {"/public/index.do", "/", "/public/"})
    public String index(ModelMap model) {
        model.addAttribute("post", blogService.getFirstPost());
      
        return "public/index";
    }

}   
