package com.cupitmadland.art_collector_registration.controller;

import com.cupitmadland.art_collector_registration.entity.ArtCollector;
import com.cupitmadland.art_collector_registration.service.ArtCollectorService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AuthoController {
    private ArtCollectorService artCollectorService;

    @Autowired
    public AuthoController(ArtCollectorService artCollectorService) {
        this.artCollectorService = artCollectorService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        ArtCollector collector = new ArtCollector();
        model.addAttribute("Collector", collector);
        return "artcollectorregistration";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("Collector") ArtCollector collector, BindingResult result,
                               Model model) {
        ArtCollector existingCollector = artCollectorService.findArtCollectorByEmail(collector.getEmail());

        if (existingCollector != null && existingCollector.getEmail() != null && !existingCollector.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("Collector", collector);
            return "/artcollectorregistration";
        }
        artCollectorService.saveArtCollector(collector);
        return "redirect:/register?success";
    }
    @GetMapping("/artcollectors")
    public String collector(Model model) {
        List<ArtCollector> collectors = artCollectorService.findAllArtCollector();

        model.addAttribute("collector", collectors);

        return "registeredartcollector";

    }




}









