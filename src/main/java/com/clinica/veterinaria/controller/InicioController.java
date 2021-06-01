package com.clinica.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class InicioController {
    
    private static final String INICIO_INDEX ="index";
    private static final String NOSOTROS_INDEX ="nosotros";
    private static final String SEDES_INDEX ="sedes";
    private static final String SERVICIOS_INDEX ="servicios";

    @GetMapping("/")
    public String index(Model model) {
        return INICIO_INDEX;
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        return NOSOTROS_INDEX;
    }

    @GetMapping("/sedes")
    public String sedes(Model model) {
        return SEDES_INDEX;
    }
    
    @GetMapping("/servicios")
    public String servicios(Model model) {
        return SERVICIOS_INDEX;
    }
}