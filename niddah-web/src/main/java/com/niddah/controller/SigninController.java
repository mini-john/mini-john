/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.core.entity.Femme;
import com.niddah.core.service.FemmeService;
import com.niddah.library.constante.Constantes;
import com.niddah.library.dto.FemmeDto;
import com.niddah.library.enumeration.EtatAccount;
import com.niddah.library.enumeration.RoleUser;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author mini-john
 */
@Controller
public class SigninController {

    @Autowired
    FemmeService femmeService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, value = "/public/signin.do")
    public String getSignIn(ModelMap model) {
        model.addAttribute("femme", new FemmeDto());
        return "public/signin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/public/signup.do")
    public String getSignUp(ModelMap model) {

        return "public/signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/public/signin.do")
    public ModelAndView signin(@Valid @ModelAttribute("femme") FemmeDto femmeDto, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        if (result.hasErrors()) {

            return new ModelAndView("public/signin", "femme", femmeDto);
        }
        try {
            femmeDto.getAccount().setRole(RoleUser.Femme);
            //TODO : mettre genereation jeton et date limite jeton
            femmeDto.getAccount().setEtatAccount(EtatAccount.creation);
            Calendar cal = GregorianCalendar.getInstance();
            cal.add(Calendar.HOUR_OF_DAY, Constantes.NB_HOUR_JETON_VALIDE);
            femmeDto.getAccount().setDateLimiteJeton(cal.getTime());
            femmeDto.getAccount().setJeton(BigDecimal.valueOf(Math.random()));
            Long femmeDtoId = femmeService.add(femmeDto, Femme.class);
            femmeDto.setId(femmeDtoId);
            //TODO : rajouter fonction envoi mail d'inscription
            //mailService.envoiMailInscription(femmeDto)
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException cdts = (ConstraintViolationException) ex.getCause();
                if (cdts.getConstraintName().equalsIgnoreCase("emailExist")) {
                    ObjectError error = new ObjectError("mail", messageSource.getMessage("Account.add.error.mail.exist", new String[]{femmeDto.getAccount().getMail()}, new Locale("fr")));
                    result.addError(error);
                    return new ModelAndView("public/signin", "femme", femmeDto);
                }
                if (cdts.getConstraintName().equalsIgnoreCase("loginExist")) {
                    ObjectError error = new ObjectError("login", messageSource.getMessage("Account.add.error.login.exist", new String[]{femmeDto.getAccount().getLogin()}, new Locale("fr")));
                    result.addError(error);
                    return new ModelAndView("public/signin", "femme", femmeDto);
                }
            }
        }
        return new ModelAndView("public/signinsuccess");

    }

    @RequestMapping(value = "/public/verify.do", method = RequestMethod.GET)
    public ModelAndView verifyAccount(@RequestParam("id") Long femmeId, @RequestParam("jeton") BigDecimal jeton) {
        FemmeDto femmeDto = femmeService.getById(femmeId, Femme.class, FemmeDto.class);
        Date date = GregorianCalendar.getInstance().getTime();
        if (date.compareTo(femmeDto.getAccount().getDateLimiteJeton()) > 0 || jeton != femmeDto.getAccount().getJeton()) {
            return new ModelAndView("public/verifyfail");

        } else {
            return new ModelAndView("public/verifysuccess");

        }

    }

    @RequestMapping(value = "/public/addPassword.do", method = RequestMethod.POST)
    public ModelAndView addPaswword(@RequestParam("id") Long femmeId,
            @RequestParam("password") String password,
            BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        return new ModelAndView("public/accountCreate");

    }

}
