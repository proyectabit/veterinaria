package com.clinica.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import javax.validation.Valid;

import com.clinica.veterinaria.model.Contacto;
import com.clinica.veterinaria.reposity.ContactoRepository;

@Controller
public class ContactoController {

    private static final String CONTACTO_INDEX ="contacto";
    private static String MODEL_CONTACT = "contact";
    private final ContactoRepository contactsData;

    public ContactoController(ContactoRepository contactsData){
        this.contactsData = contactsData;
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute(MODEL_CONTACT, new Contacto());
        return CONTACTO_INDEX;
    }

    @PostMapping("/contacto")
    public String createSubmitForm(Model model, @Valid Contacto objContact, BindingResult result ){
        if(result.hasFieldErrors()) {
            model.addAttribute("mensaje", "No se registro su mensaje.");
        } else {
            this.contactsData.save(objContact);
            model.addAttribute(MODEL_CONTACT, objContact);
            model.addAttribute("mensaje", "Se registro su mensaje.");
        }
        return CONTACTO_INDEX;
    }
    
}
