package com.clinica.veterinaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AdministradorController {
        
    private static final String CERRAR_SESION ="admin/signout";
    private static final String SIDEBAR_INDEX ="admin/sidebar";
    private static final String CONTACT_INDEX ="admin/contact";
    private static final String DASHBOARD_INDEX ="admin/dashboard copy";
    private static final String PRODUCT_CREATE ="admin/product-create";
    private static final String PRODUCT_DELETE ="admin/product-delete";
    private static final String PRODUCT_EDIT ="admin/product-edit";
    private static final String PRODUCT_LIST ="admin/product-list";
    private static final String EDIT_PASS ="admin/profile-edit-password";
    private static final String EDIT_INDEX ="admin/profile-edit";
    private static final String VISTA_INDEX ="admin/profile-show";
    private static final String COMPRA_INDEX ="admin/shooping";
    private static final String CREAR_USUARIO ="admin/user-create";
    private static final String ELIMINAR_USUARIO ="admin/user-delete";
    private static final String EDITAR_USUARIO ="admin/user-edit";
    private static final String LISTA_USUARIO ="admin/user-list";

    @GetMapping("/signout")
    public String signout(Model model) {
        return CERRAR_SESION;
    }

    @GetMapping("/sidebar")
    public String sidebarIndex(Model model) {
        return SIDEBAR_INDEX;
    }

    @GetMapping("/contact")
    public String contactIndex(Model model) {
        return CONTACT_INDEX;
    }

    @GetMapping("/dashboard copy")
    public String dashboardIndex(Model model) {
        return DASHBOARD_INDEX;
    }

    @GetMapping("/product-create")
    public String productcreate(Model model) {
        return PRODUCT_CREATE;
    }

    @GetMapping("/product-delete")
    public String productdelete(Model model) {
        return PRODUCT_DELETE;
    }

    @GetMapping("/product-edit")
    public String productedit(Model model) {
        return PRODUCT_EDIT;
    }
    @GetMapping("/product-list")
    public String productlist(Model model) {
        return PRODUCT_LIST;
    }
    @GetMapping("/profile-edit-password")
    public String editpass(Model model) {
        return EDIT_PASS;
    }
    @GetMapping("/profile-edit")
    public String editindex(Model model) {
        return EDIT_INDEX;
    }
    @GetMapping("/profile-show")
    public String vistaindex(Model model) {
        return VISTA_INDEX;
    }
    @GetMapping("/shooping")
    public String compraindex(Model model) {
        return COMPRA_INDEX;
    }
    @GetMapping("/user-create")
    public String crearusuario(Model model) {
        return CREAR_USUARIO;
    }
    @GetMapping("/user-delete")
    public String eliminarusuario(Model model) {
        return ELIMINAR_USUARIO;
    }
    @GetMapping("/user-edit")
    public String editarusuario(Model model) {
        return EDITAR_USUARIO;
    }
    @GetMapping("/user-list")
    public String listausuario(Model model) {
        return LISTA_USUARIO;
    }



}
