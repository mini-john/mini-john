/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller.admin;

import com.niddah.core.entity.blog.Comments;
import com.niddah.core.entity.blog.Post;
import com.niddah.core.service.BlogService;
import com.niddah.library.dto.AccountDto;
import com.niddah.library.dto.blog.CommentsDto;
import com.niddah.library.dto.blog.PostDto;
import java.util.Date;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
        model.addAttribute("posts", blogService.allPostWithPagination(offset, (maxResults == null) ? 5 : maxResults));
        model.addAttribute("count", blogService.count());
        model.addAttribute("offset", offset);
        return "public/blog/index";
    }

    @RequestMapping("/public/blog/view.do")
    public String publicView(ModelMap modelMap, @RequestParam("id") Long id) {
        modelMap.addAttribute("post", blogService.getById(id, Post.class, PostDto.class));
        modelMap.addAttribute("comment", new CommentsDto());
        return "public/blog/view";
    }

    @RequestMapping(value = "/public/blog/addComment.do", method = RequestMethod.POST)
    public String addComment(ModelMap modelMap, @Valid @ModelAttribute("comment") CommentsDto comment, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {

            modelMap.addAttribute("post", blogService.getById(comment.getPost().getId(), Post.class, PostDto.class));
            modelMap.addAttribute("comment", comment);
            modelMap.put("org.springframework.validation.BindingResult.comment", result);
            return "public/blog/view";
        }
        LOGGER.info("Rajout d'un commentaire sur l'article d'id {} par {} d'adresse mail {}", comment.getPost().getId(), comment.getAutheur(), comment.getEmail());
        comment.setDateComments(new Date());
        blogService.add(comment, Comments.class);
        modelMap.addAttribute("post", blogService.getById(comment.getPost().getId(), Post.class, PostDto.class));
        modelMap.addAttribute("comment", new CommentsDto());

        return "redirect:view.do?id=" + comment.getPost().getId();
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
        postDto.setAuthor((AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDto = blogService.add(postDto, Post.class);
        LOGGER.info("Rajout d'un article par {} de titer", postDto.getAuthor().getId(), postDto.getTitle());
        modelMap.addAttribute("post", postDto);
        return "admin/blog/view";
    }

    @RequestMapping("/admin/blog/update.do")
    public String adminupdate(ModelMap modelMap, @RequestParam("id") Long id) {
        modelMap.addAttribute("post", blogService.getById(id, Post.class, PostDto.class));
        return "admin/blog/update";
    }

    @RequestMapping(value = "/admin/blog/update.do", method = RequestMethod.POST)
    public String updatePostPost(ModelMap modelMap, @Valid @ModelAttribute PostDto postDto, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {

            return "admin/blog/update";
        }
        postDto.setAuthor((AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LOGGER.info("Modification d'un article par {} de titer", postDto.getAuthor().getId(), postDto.getTitle());

        postDto = blogService.merge(postDto, Post.class);
        return "redirect:view.do?id=" + postDto.getId();
    }

    @RequestMapping("/admin/blog/view.do")
    public String adminView(ModelMap modelMap, @RequestParam("id") Long id) {
        modelMap.addAttribute("post", blogService.getById(id, Post.class, PostDto.class));
        return "admin/blog/view";
    }

    @RequestMapping("/admin/blog/delete.do")
    public RedirectView adminDelete(ModelMap modelMap, @RequestParam("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        blogService.delete(blogService.getById(id, Post.class, PostDto.class), Post.class);
        LOGGER.info("Suppression de l'article {} par {}", id, (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("Post.delete.success", new String[]{""}, new Locale("fr")));
        return new RedirectView("index.do", true);
    }

    @RequestMapping("/admin/blog/index.do")
    public String adminIndex(ModelMap model, Integer offset, Integer maxResults) {
        model.addAttribute("posts", blogService.allPostWithPagination(offset, (maxResults == null) ? 5 : maxResults));
        model.addAttribute("count", blogService.count());
        model.addAttribute("offset", offset);
        return "admin/blog/index";
    }

    @RequestMapping("/admin/blog/moderation.do")
    public String adminModeration(ModelMap model, Integer offset, Integer maxResults, @RequestParam("id") Long id) {
        LOGGER.debug("offet {}, maxResults{}", offset, maxResults);
        model.addAttribute("comments", blogService.allCommentWithPagination(id, offset, (maxResults == null) ? 5 : maxResults));
        model.addAttribute("count", blogService.countComment(id));
        model.addAttribute("offset", offset);
        return "admin/blog/moderation";
    }

    @RequestMapping("/admin/blog/deleteComment.do")
    public RedirectView adminDeleteComment(ModelMap modelMap, @RequestParam("id") Long id, RedirectAttributes redirectAttributes, Locale locale) {
        CommentsDto comment = blogService.getById(id, Comments.class, CommentsDto.class);
        blogService.delete(comment, Comments.class);
        LOGGER.info("Suppression du commentaire de {} de l'article {} par {}", comment.getEmail(), id, (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", messageSource.getMessage("Comments.delete.success", new String[]{""}, new Locale("fr")));
        return new RedirectView("moderation.do?id=" + comment.getPost().getId(), true);
    }

}
