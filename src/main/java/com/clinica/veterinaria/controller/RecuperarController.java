package com.clinica.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class RecuperarController {
        
    private static final String RECUPERAR_INDEX ="account/recuperar";

    @GetMapping("/recuperar")
    public String index(Model model) {
        return RECUPERAR_INDEX;
    }

}
