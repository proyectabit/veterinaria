package com.clinica.veterinaria.controller;

import java.util.List;
import java.util.Optional;

import com.clinica.veterinaria.model.Producto;
import com.clinica.veterinaria.model.Categoria;
import com.clinica.veterinaria.reposity.ProductoRepository;
import com.clinica.veterinaria.reposity.CategoriaRepository;
import com.clinica.veterinaria.model.Usuario;
import com.clinica.veterinaria.reposity.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
public class CatalogoController {
    
    private static final String INDEX ="catalogo/index";
    private static final String INDEX_VER ="catalogo/individual";
    private static final String INDEX_CARRITO ="catalogo/carrito_add";
    private final ProductoRepository productsData;
    private final CategoriaRepository categoryData;
    

    public CatalogoController(ProductoRepository productsData, CategoriaRepository categoryData){
        this.productsData = productsData;
        this.categoryData = categoryData;
    }      

    @GetMapping("/catalogo/index")
    public String index(
        @RequestParam(defaultValue="") String searchName,
        Model model){
        List<Producto> listProducto = this.productsData.getAllActiveProductos();
        List<Categoria> listCategoria = this.categoryData.getAllActiveCategorias();
        model.addAttribute("products", listProducto);
        model.addAttribute("categorys", listCategoria);
        return INDEX;
    } 

    @GetMapping("/catalogo/ver/{id}")
    public String ver(@PathVariable("id") Integer id, Model model){
        Producto productSeleccionado = productsData.getOne(id);
        List<Producto> listProductoCategoria = this.productsData.getAllActiveProductosByCategory(productSeleccionado.getCategoria(), productSeleccionado.getId());
        model.addAttribute("product", productSeleccionado);
        model.addAttribute("productCategoria", listProductoCategoria);
        return INDEX_VER;
    }





    @GetMapping("/catalogo/carrito/add/{id}")
    public String carrito(@PathVariable("id") Integer id, Model model){
        model.addAttribute("products", "");
        return INDEX_CARRITO;
    }

}