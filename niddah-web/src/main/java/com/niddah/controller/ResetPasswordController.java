/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.component.MailSenderNiddah;
import com.niddah.core.entity.Account;
import com.niddah.core.entity.Personne;
import com.niddah.core.service.PersonneService;
import com.niddah.library.constante.Constantes;
import com.niddah.library.dto.AccountDto;
import com.niddah.library.dto.PersonneDto;
import com.niddah.library.enumeration.EtatAccount;
import com.niddah.library.exception.NiddahDataException;
import com.niddah.web.NiddahToken;
import com.niddah.web.validator.PasswordValidator;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @author Boccara Jonathan
 */
@Controller
public class ResetPasswordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordController.class);
    @Autowired
    PersonneService personneService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MailSenderNiddah mailSenderNiddah;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/public/reset/index.do")
    public String index(Model m) {
        LOGGER.info("La page de mot de passe oublié est demandée");
        m.addAttribute("account", new AccountDto());
        return "public/reset/index";
    }

    @RequestMapping(value = "/public/reset/verify.do", method = RequestMethod.POST)
    public String verify(@ModelAttribute("account") AccountDto account, BindingResult result, RedirectAttributes redirectAttributes, Locale locale, Model m) {
        LOGGER.info("L'utilisateur avec l'adresse mail {} demande un nouveau mot de passe", account.getMail());
        //recherche du compte en base
        PersonneDto personneFind = personneService.getByEmail(account.getMail());
        if (personneFind == null) {
            LOGGER.info("L'utilisateur avec le mail {} n'existe pas", account.getMail());
            result.rejectValue("mail", "reset.password.mail.notExist", messageSource.getMessage("reset.password.mail.notExist", new String[]{account.getMail()}, new Locale("fr")));
            // m.addAttribute("account", account);
            return "public/reset/index";
        }
        Calendar cal = GregorianCalendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, Constantes.NB_HOUR_JETON_VALIDE);
        personneFind.getAccount().setDateLimiteJeton(cal.getTime());
        personneFind.getAccount().setJeton(NiddahToken.getToken());
        personneFind.getAccount().setEtatAccount(EtatAccount.changementMotDePasse);
        personneService.update(personneFind, Personne.class);
        LOGGER.info("Envoi du mail de nouveau mot de passe à l'adresse {} pour le compte {}", personneFind.getAccount().getMail(), personneFind.getAccount().getLogin());
        mailSenderNiddah.sendMailResetPassword(personneFind);

        return "public/reset/verify";
    }

    @RequestMapping(value = "/public/reset/verify.do", method = RequestMethod.GET)
    public ModelAndView verifyAccount(@RequestParam("id") Long personneId, @RequestParam("jeton") String jeton, Model map) {
        LOGGER.info("Verification du lien de reset password pour le compte d'id :{}, et le jeton {}", personneId, jeton);

        PersonneDto personneDto = personneService.getById(personneId, Personne.class, PersonneDto.class);

        Date date = GregorianCalendar.getInstance().getTime();
        if (personneDto == null || personneDto.getAccount().getEtatAccount() != EtatAccount.changementMotDePasse || date.compareTo(personneDto.getAccount().getDateLimiteJeton()) > 0 || !jeton.equals(personneDto.getAccount().getJeton())) {
            LOGGER.info("Lien incorrect id:{},jeton:{}", personneId, jeton);

            return new ModelAndView("public/reset/verifyfail");

        } else {
            LOGGER.info("Lien correct id:$1,jeton:$2", personneId, jeton);
            map.addAttribute("personne", personneDto);
            return new ModelAndView("public/reset/verifysuccess");

        }

    }

    @RequestMapping(value = "/public/reset/changePassword.do", method = RequestMethod.POST)
    public ModelAndView addPaswword(@ModelAttribute("personne") PersonneDto personneDto,
            BindingResult result, RedirectAttributes redirectAttributes, Locale locale, Model map) throws NiddahDataException {
        PersonneDto personneFromDB = personneService.getById(personneDto.getId(), Personne.class, PersonneDto.class);
        result = new BeanPropertyBindingResult(personneDto, "personne");
        LOGGER.info("Modification de mot de passe pour la personne $1", personneDto.getId());

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
        LOGGER.info("Envoi du mail de mise a jour du password pour le login:{} et mail:{}", personneFromDB.getAccount().getLogin(), personneFromDB.getAccount().getMail());

        mailSenderNiddah.sendMailPasswordChange(personneFromDB, personneDto.getAccount().getPassword());

        return new ModelAndView("public/reset/passwordChange");

    }

}
