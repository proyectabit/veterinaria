package com.clinica.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import javax.validation.Valid;

import com.clinica.veterinaria.model.Usuario;
import com.clinica.veterinaria.reposity.UsuarioRepository;

@Controller
public class CrearcuentaController {
            
    private static final String CREAR_INDEX ="account/crear";
    private static String MODEL_CREAR_CUENTA = "crears";
    private final UsuarioRepository usersData;

    public CrearcuentaController(UsuarioRepository usersData){
        this.usersData = usersData;
    }

    @GetMapping("/crearcuenta")
    public String index(Model model) {
        model.addAttribute(MODEL_CREAR_CUENTA, new Usuario());
        return CREAR_INDEX;
    }

    @PostMapping("/crearcuenta")
    public String createSubmitForm(Model model, @Valid Usuario objUsers, BindingResult result ){
        if(result.hasFieldErrors()) {
            model.addAttribute("mensaje", "No se creado su cuenta.");
        } else {
            this.usersData.save(objUsers);
            model.addAttribute(MODEL_CREAR_CUENTA, objUsers);
            model.addAttribute("mensaje", "Se creo su cuenta.");
        }
        return CREAR_INDEX;
    }

}
