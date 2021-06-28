package com.clinica.veterinaria.controller;

import java.util.Optional;
import javax.validation.Valid;
import com.clinica.veterinaria.model.Usuario;
import com.clinica.veterinaria.reposity.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class IngresarController {
            
    private static final String INGRESAR_INDEX ="account/ingresar";
    private static final String PERFIL_INDEX ="cliente/perfil";
    private static String MODEL_LOGIN = "login";
    private static String MODEL_MESSAGE = "mensaje";
    private final UsuarioRepository usersData;

    public IngresarController(UsuarioRepository usersData){
        this.usersData = usersData;
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
    public String perfil(Model model, HttpSession session, HttpServletRequest request) {
        String codigoVerificacion = request.getSession().getId();
        //List<Usuario> listItems = this.usersData.findById();
        model.addAttribute("usuariox", codigoVerificacion);
        return PERFIL_INDEX;
    }




}
