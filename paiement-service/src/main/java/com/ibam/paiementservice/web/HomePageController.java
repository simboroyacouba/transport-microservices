package com.ibam.paiementservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/paiement")
    public String redirectToPayment() {
        return "redirect:/";
    } @GetMapping("/api/paiements/accueil")
    public String redirectTonPayment() {
        return "redirect:/";
    }
}
