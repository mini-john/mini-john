/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.web.controller.moncompte;

import com.jkalvered.core.dto.AccountDto;
import com.jkalvered.core.dto.tool.HalahaDto;
import com.jkalvered.core.dto.tool.Localisation;
import com.jkalvered.core.service.PersonneService;
import com.jkalvered.web.controller.tool.BindingResultTools;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jonat
 */
@Controller
public class MonCompteController {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    PersonneService personneService;
    Set<String> allTimezone = ZoneId.getAvailableZoneIds();
    List<String> allTimeZoneSort = new ArrayList<>(allTimezone);

    @InitBinder()
    private void initBinder(WebDataBinder binder) {

    }

    @PostConstruct
    public void init() {
        LOGGER.info("Instantiation du MonCompteController");
        Collections.sort(allTimeZoneSort);
    }

    @RequestMapping(value = {"/private/moncompte/index.do"})
    public String index(ModelMap map) {
        LOGGER.info("La page index est demandée DJFKDH");

        return "private/moncompte/index";
    }

    @RequestMapping(value = {"/private/moncompte/halaha.do"})
    public String halaha(HalahaDto halaha, ModelMap map) {
        AccountDto principal = (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("L'utilisateur d'id={} a demande la page de gestion de préférence halakhique ", principal.getId());
        halaha.setBneTorah(principal.getPersonne().getConfiguration().isBneTorah());
        halaha.setOrigine(principal.getPersonne().getConfiguration().getOrigine());
        halaha.setDoMohDahouk(principal.getPersonne().getConfiguration().isDoMohDahouk());
        halaha.setPrichaBenonitHovotDaat(principal.getPersonne().getConfiguration().isPrichaBenonitHovotDaat());
        halaha.setPrichaHoutChani(principal.getPersonne().getConfiguration().isPrichaHoutChani());
        halaha.setPrichaOrZaroua(principal.getPersonne().getConfiguration().isPrichaOrZaroua());
        halaha.setPrihaHovotYair(principal.getPersonne().getConfiguration().isPrihaHovotYair());
        return "private/moncompte/halaha";
    }

    @RequestMapping(value = {"/private/moncompte/localisation.do"}, method = RequestMethod.GET)
    public String getLocalisation(Localisation localisation, ModelMap map) {
        AccountDto principal = (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.info("L'utilisateur d'id={} a demande la page de localisation ", principal.getId());
        map.addAttribute("allTimeZone", allTimeZoneSort);
        map.addAttribute("account", principal);
        localisation.setLatitude(principal.getPersonne().getConfiguration().getLatitude());
        localisation.setLongitude(principal.getPersonne().getConfiguration().getLongitude());
        localisation.setElevation(principal.getPersonne().getConfiguration().getElevation());
        localisation.setLocationName(principal.getPersonne().getConfiguration().getLocationName());
        localisation.setTimeZone(principal.getPersonne().getConfiguration().getTimeZone());
        return "private/moncompte/localisation";
    }

    @RequestMapping(value = {"/private/moncompte/savelocalisation.do"}, method = RequestMethod.POST)
    public String postLocalisation(HttpServletRequest request, @Validated() Localisation localisation, BindingResult br, ModelMap map) {
        LOGGER.info("j'essaie de persister");

        if (br.hasErrors()) {
            map.addAttribute("org.springframework.validation.BindingResult.localisation", BindingResultTools.deleteDuplicateError(br, localisation, "localisation"));
            map.addAttribute("allTimeZone", allTimeZoneSort);
            return "private/moncompte/localisation";

        }
        LOGGER.info("On sauvegarde une localisation ");
        AccountDto principal = (AccountDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        map.addAttribute("allTimeZone", allTimeZoneSort);
        map.addAttribute("account", principal);
        LOGGER.info("voila la localisation" + localisation);
        personneService.updateLocalisation(principal.getPersonne().getId(), localisation);
        return "private/moncompte/localisationSuccess";

    }

}
