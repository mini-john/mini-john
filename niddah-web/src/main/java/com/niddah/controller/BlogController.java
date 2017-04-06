/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.core.entity.blog.Post;
import com.niddah.core.service.BlogService;
import com.niddah.library.dto.blog.PostDto;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Boccara Jonathan
 */
@Controller
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/public/blog/index.do")
    public String index(ModelMap model, Integer offset, Integer maxResults) {
        model.addAttribute("posts", blogService.allPostWithPagination(offset, maxResults));
        model.addAttribute("count", blogService.count());
        model.addAttribute("offset", offset);
        return "public/blog/index";
    }

    @RequestMapping(value = "/admin/blog/add.do", method = RequestMethod.GET)
    public String addPost(ModelMap modelMap) {
        modelMap.addAttribute("post", new PostDto());
        return "admin/blog/add";
    }

    @RequestMapping(value = "/admin/blog/add.do", method = RequestMethod.POST)
    public String addPostPost(ModelMap modelMap, @Valid @ModelAttribute PostDto postDto, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {

            return "admin/blog/add";
        }
        postDto = blogService.add(postDto, Post.class);
        modelMap.addAttribute("post", postDto);
        return "admin/blog/view";
    }

    @RequestMapping("/admin/blog/view.do")
    public String adminView(ModelMap modelMap, @RequestParam("id") Long id) {
        modelMap.addAttribute("post", blogService.getById(id, Post.class, PostDto.class));
        return "admin/blog/view";
    }

    @RequestMapping("/public/blog/view.do")
    public String publicView(ModelMap modelMap, @RequestParam("id") Long id) {
        modelMap.addAttribute("post", blogService.getById(id, Post.class, PostDto.class));
        return "public/blog/view";
    }

}
