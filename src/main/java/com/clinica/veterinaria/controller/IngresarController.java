package com.clinica.veterinaria.controller;

import java.util.Optional;
import javax.validation.Valid;
import java.util.List;
import com.clinica.veterinaria.model.Usuario;
import com.clinica.veterinaria.reposity.UsuarioRepository;
import com.clinica.veterinaria.model.Proforma;
import com.clinica.veterinaria.reposity.ProformaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IngresarController {
            
    private static final String INGRESAR_INDEX ="account/ingresar";
    private static final String PERFIL_INDEX ="cliente/perfil";
    private static final String INDEX_DASHBOARD ="cliente/dashboard"; 
    private static String MODEL_LOGIN = "login";
    private static String MODEL_MESSAGE = "mensaje";
    private final UsuarioRepository usersData;
    private final ProformaRepository proformaData;

    public IngresarController(UsuarioRepository usersData, ProformaRepository proformaData){
        this.usersData = usersData;
        this.proformaData = proformaData;
    }

    @GetMapping("/ingresar")
    public String index(Model model) {
        model.addAttribute(MODEL_LOGIN, new Usuario());
        return INGRESAR_INDEX;
    }

    @PostMapping("/ingresar")
    public String createSubmitForm(Model model, @Valid Usuario objUser, HttpServletRequest request, BindingResult result, HttpSession session) {
        String page = INGRESAR_INDEX;
        model.addAttribute(MODEL_LOGIN, new Usuario());
        if(result.hasFieldErrors()) {
            model.addAttribute(MODEL_MESSAGE, "No se ha podido loguear.");
        } else {
            Optional<Usuario> userDB = this.usersData.findById(objUser.getId());
            if (userDB.isPresent()) {
                if(userDB.get().getContrasena().equals(objUser.getContrasena())){
                    model.addAttribute(MODEL_LOGIN, userDB.get());
                    model.addAttribute(MODEL_MESSAGE, "Usuario existe");
                    request.getSession().setAttribute("user", objUser);
                    request.getSession().setAttribute("id", userDB.get().getId());
                    request.getSession().setAttribute("nombre", userDB.get().getNombre());
                    request.getSession().setAttribute("paterno", userDB.get().getAppaterno());
                    Usuario user = (Usuario)session.getAttribute("user");
                    List<Proforma> count_carrito = this.proformaData.findItemsByUsuario(user);
                    request.getSession().setAttribute("count_carrito", count_carrito.size());
                    page = "cliente/dashboard";
                }else{
                    model.addAttribute(MODEL_MESSAGE, "Contraseña no coincide");  
                }
            } else {
                model.addAttribute(MODEL_MESSAGE, "Usuario no existe.");
            }
        }
        return page;
    }

    @GetMapping("/logout")
	public String logoutSession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}


    @GetMapping("/usuario/perfil")
    public String perfil(HttpSession session, Model model){
        Usuario user = (Usuario)session.getAttribute("user"); 
        if(user == null) {
            model.addAttribute("mensaje", "Debe loguearse antes de ir a la página perfil.");
            model.addAttribute(MODEL_LOGIN, new Usuario());
            return INGRESAR_INDEX;
        }   
        return PERFIL_INDEX;
    }  

    @GetMapping("/usuario/dashboard")
    public String dashboard(HttpSession session, Model model){
        Usuario user = (Usuario)session.getAttribute("user"); 
        if(user == null) {
            model.addAttribute("mensaje", "Debe loguearse antes de ir a la página principal.");
            model.addAttribute(MODEL_LOGIN, new Usuario());
            return INGRESAR_INDEX;
        }   
        return INDEX_DASHBOARD;
    }  




}
