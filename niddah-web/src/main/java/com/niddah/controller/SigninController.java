/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.captcha.CaptchaService;
import com.niddah.component.MailSenderNiddah;
import com.niddah.controller.error.ReCaptchaInvalidException;
import com.niddah.core.entity.Account;
import com.niddah.core.entity.Personne;
import com.niddah.core.service.PersonneService;
import com.niddah.library.constante.Constantes;
import com.niddah.library.dto.PersonneDto;
import com.niddah.library.enumeration.EtatAccount;
import com.niddah.library.enumeration.RoleUser;
import com.niddah.library.exception.NiddahDataException;
import com.niddah.web.NiddahToken;
import com.niddah.web.validator.PasswordValidator;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mobile.device.Device;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SigninController.class);
    @Autowired
    PersonneService personneService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MailSenderNiddah mailSenderNiddah;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/public/signin.do")
    public String getSignIn(ModelMap model, Device device) {
        model.addAttribute("personne", new PersonneDto());
        model.addAttribute("captchaService", captchaService);
        model.addAttribute("device", device);
        LOGGER.info("La page de création de compte est demandé");
        return "public/signin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/public/signup.do")
    public String getSignUp(ModelMap model) {
        LOGGER.info("La page de connection est demandée");
        return "public/signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/public/signin.do")
    public String signin(@Valid @ModelAttribute("personne") PersonneDto personneDto,
            BindingResult result, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value = "g-recaptcha-response", required = false) String grecaptcharesponse, Model m, Device device) {
        m.addAttribute("captchaService", captchaService);
        m.addAttribute("device", device);
        LOGGER.info("Une demande de création de compte est demandé avec pour adresse mail {} et pour login {}",
                personneDto.getAccount().getMail(), personneDto.getAccount().getLogin());
        if (device.isNormal()) {
            try {
                LOGGER.info("Verification du CAPTCHA");
                captchaService.processResponse(grecaptcharesponse);
            } catch (ReCaptchaInvalidException ex) {
                result.rejectValue("captcha", "Captcha.error", messageSource.getMessage("Captcha.error", new String[]{}, new Locale("fr")));
                LOGGER.info("Erreur de validation du captcha : {}", ex);

                return "public/signin";
            }
        }

        if (result.hasErrors()) {
            LOGGER.info("Le formulaire de création de compte comptient des erreurs pour $1", personneDto.getAccount().getLogin());
            return "public/signin";
        }
        switch (personneDto.getSexe()) {
            case Femme:
                personneDto.getAccount().setRole(RoleUser.Femme);
                break;
            case Homme:
                personneDto.getAccount().setRole(RoleUser.Homme);

                break;
        }

        personneDto.getAccount().setEtatAccount(EtatAccount.creation);
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, Constantes.NB_HOUR_JETON_VALIDE);
        personneDto.getAccount().setDateLimiteJeton(cal.getTime());
        personneDto.getAccount().setJeton(NiddahToken.getToken());
        //personneDto.getAccount().setPersonne(personneDto);
        PersonneDto personneExist;
        if (personneService.isMailActifExist(personneDto.getAccount().getMail())) {
            LOGGER.info("Un compte actif avec cette adresse mail actif exist {}", personneDto.getAccount().getMail());
            result.rejectValue("account.mail", "Account.add.error.mail.exist", messageSource.getMessage("Account.add.error.mail.exist", new String[]{personneDto.getAccount().getMail()}, new Locale("fr")));
            return "public/signin";
        }
        if (personneService.isLoginActifExist(personneDto.getAccount().getLogin())) {
            LOGGER.info("Un compte  actif existe avec ce login {} ", personneDto.getAccount().getLogin());
            result.rejectValue("account.login", "Account.add.error.login.exist", messageSource.getMessage("Account.add.error.login.exist", new String[]{personneDto.getAccount().getLogin()}, new Locale("fr")));

            return "public/signin";
        }
        if ((personneExist = personneService.isMailCreationExist(personneDto.getAccount().getMail())) != null) {
            LOGGER.info("Un compte en création avec cette adresse mail actif exist {}", personneDto.getAccount().getMail());

            result.rejectValue("account.mail", "Account.add.error.mail.exist", messageSource.getMessage("Account.add.error.mail.exist", new String[]{personneDto.getAccount().getMail()}, new Locale("fr")));
            return "redirect:signinExist.do?id=" + personneExist.getId() + "&type=1";
        }
        if ((personneExist = personneService.isLoginCreationExist(personneDto.getAccount().getLogin())) != null) {
            LOGGER.info("Un compte  en créaion existe avec ce login {} ", personneDto.getAccount().getLogin());

            result.rejectValue("account.login", "Account.add.error.login.exist", messageSource.getMessage("Account.add.error.login.exist", new String[]{personneDto.getAccount().getLogin()}, new Locale("fr")));
            return "redirect:signinExist.do?id=" + personneExist.getId() + "&type=2";
        }
        personneDto = personneService.add(personneDto, Personne.class);
        LOGGER.info("L'ajout de l'utilisateur {}", personneDto.getAccount().getLogin());
        LOGGER.info("Envoi du mail d'inscription pour le mail {}", personneDto.getAccount().getMail());
        mailSenderNiddah.sendMailActivationAccount(personneDto);

        return "public/signinsuccess";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/public/signinExist.do")
    public String signinExist(ModelMap m, @RequestParam("id") Long id, @RequestParam("type") Integer type) throws NiddahDataException {
        LOGGER.info("reprise de création de comte en création {}", id);
        PersonneDto personneDto = personneService.getById(id, Personne.class, PersonneDto.class);

        if (personneDto == null || personneDto.getAccount().getEtatAccount() != EtatAccount.creation) {

            LOGGER.error("Le compte d'id {} n'est pas en activation ou n'existe pas", id);
            throw new NiddahDataException("Une erreur de recherche de compte est apparu");
        }
        if (type != 1 && type != 2) {
            throw new NiddahDataException("Le compte n'est pas en état d'activation");

        }
        switch (type) {
            case 1:
                m.addAttribute("exist", "une adresse mail identique");
                break;
            case 2:
                m.addAttribute("exist", "un identifiant identique");

                break;

        }
        m.addAttribute("personne", personneDto);
        return "public/signinExist";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/public/validSignin.do")
    public String validSignin(@Valid @ModelAttribute("personne") PersonneDto personneDto, BindingResult result, RedirectAttributes redirectAttributes, Locale locale, Model m) {

        if (result.hasErrors()) {

            return "public/signinExist";
        }
        personneDto = personneService.getById(personneDto.getId(), Personne.class, PersonneDto.class);
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, Constantes.NB_HOUR_JETON_VALIDE);
        personneDto.getAccount().setDateLimiteJeton(cal.getTime());
        personneDto.getAccount().setJeton(NiddahToken.getToken());

        personneService.update(personneDto, Personne.class);
        mailSenderNiddah.sendMailActivationAccount(personneDto);
        LOGGER.info("Renvoi du mail de creation de compte pour le mail {}", personneDto.getAccount().getMail());
        return "public/signinsuccess";
    }

    @RequestMapping(value = "/public/verify.do", method = RequestMethod.GET)
    public ModelAndView verifyAccount(@RequestParam("id") Long personneId, @RequestParam("jeton") String jeton, Model map) {
        LOGGER.info("Verification du lien d'activation pour le compte d'id :{}, et le jeton {}", personneId, jeton);
        PersonneDto personneDto = personneService.getById(personneId, Personne.class, PersonneDto.class);

        Date date = GregorianCalendar.getInstance().getTime();
        if (personneDto == null || personneDto.getAccount().getEtatAccount() != EtatAccount.creation || date.compareTo(personneDto.getAccount().getDateLimiteJeton()) > 0 || !jeton.equals(personneDto.getAccount().getJeton())) {
            LOGGER.info("Lien incorrect id:{},jeton:{}", personneId, jeton);
            return new ModelAndView("public/verifyfail");

        } else {
            LOGGER.info("Lien correct id:{},jeton:{}", personneId, jeton);
            map.addAttribute("personne", personneDto);
            return new ModelAndView("public/verifysuccess");

        }

    }

    @RequestMapping(value = "/public/addPassword.do", method = RequestMethod.POST)
    public ModelAndView addPaswword(@ModelAttribute("personne") PersonneDto personneDto,
            BindingResult result, RedirectAttributes redirectAttributes, Locale locale, Model map) throws NiddahDataException {
        PersonneDto personneFromDB = personneService.getById(personneDto.getId(), Personne.class, PersonneDto.class);
        result = new BeanPropertyBindingResult(personneDto, "personne");
        LOGGER.info("Rajout de mot de passe pour la personne {}", personneDto.getId());
        if (personneFromDB.getAccount().getEtatAccount() == EtatAccount.actif) {
            LOGGER.error("Le compte d'id {} est déjà actif", personneDto.getId());
            throw new NiddahDataException("Le compte est déjà actif");
        }

        PasswordValidator passwordValidator = new PasswordValidator(messageSource);
        passwordValidator.validate(personneDto, result);

        if (result.hasErrors()) {

            Map<String, Object> mapi = new HashMap<>();
            mapi.put("personne", personneFromDB);
            mapi.put("org.springframework.validation.BindingResult.personne", result);
            return new ModelAndView("public/verifysuccess", mapi);

        }

        personneFromDB.getAccount().setPassword(passwordEncoder.encode(personneDto.getAccount().getPassword()));
        personneFromDB.getAccount().setEtatAccount(EtatAccount.actif);
        personneService.update(personneFromDB.getAccount(), Account.class);
        LOGGER.info("Envoi du mail de validation de compte à login:{} et mail:{}", personneFromDB.getAccount().getLogin(), personneFromDB.getAccount().getMail());
        mailSenderNiddah.sendMailCompteCree(personneFromDB, personneDto.getAccount().getPassword());

        return new ModelAndView("public/accountCreate");

    }

}
