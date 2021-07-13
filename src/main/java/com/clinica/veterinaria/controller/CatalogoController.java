package com.clinica.veterinaria.controller;

import java.util.List;
import java.util.Optional;

import com.clinica.veterinaria.model.Producto;
import com.clinica.veterinaria.model.Categoria;
import com.clinica.veterinaria.model.Proforma;
import com.clinica.veterinaria.reposity.ProductoRepository;
import com.clinica.veterinaria.reposity.ProformaRepository;
import com.clinica.veterinaria.reposity.CategoriaRepository;
import com.clinica.veterinaria.model.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CatalogoController {
    
    private static final String INDEX ="catalogo/index";
    private static final String INDEX_VER ="catalogo/individual";
    private static final String INDEX_CATEGORIA ="catalogo/categoria";
    private static final String MIS_ORDENES_INDEX ="catalogo/ordenes";
    private final ProductoRepository productsData;
    private final CategoriaRepository categoryData;
    private final ProformaRepository proformaData;
    
    public CatalogoController(ProductoRepository productsData, CategoriaRepository categoryData, ProformaRepository proformaData){
        this.productsData = productsData;
        this.categoryData = categoryData;
        this.proformaData = proformaData;
    }      

    @GetMapping("/catalogo/ordenes")
    public String ordenes(Model model) {
        return MIS_ORDENES_INDEX;
    }

    @GetMapping("/catalogo/index")
    public String index(@RequestParam(defaultValue="") String searchName, Model model){
        List<Producto> listProducto = this.productsData.getAllActiveProductos();
        List<Categoria> listCategoria = this.categoryData.getAllActiveCategorias();
        model.addAttribute("products", listProducto);
        model.addAttribute("categorys", listCategoria);
        model.addAttribute("mensaje", "");
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

    @GetMapping("/catalogo/categoria/{id}")
    public String categoria(@PathVariable("id") Integer id, Model model){
        List<Producto> listProductoCategoria = this.productsData.getAllActiveProductosByURLCategory(id);
        List<Categoria> listCategoria = this.categoryData.getAllActiveCategorias();
        model.addAttribute("productCategoria", listProductoCategoria);
        model.addAttribute("categorys", listCategoria);
        model.addAttribute("category", id);
        return INDEX_CATEGORIA;
    }

    @GetMapping("/catalogo/carrito/add/{id}")
    public String add(@PathVariable("id") Integer id, HttpSession session, Model model, HttpServletRequest request){
        Usuario user = (Usuario)session.getAttribute("user");
        List<Producto> listProducto = this.productsData.getAllActiveProductos();
        List<Categoria> listCategoria = this.categoryData.getAllActiveCategorias();
        model.addAttribute("products", listProducto);
        model.addAttribute("categorys", listCategoria);
        if(user == null) {
            model.addAttribute("mensaje", "Debe iniciar sesión antes de añadir el prodcucto a su carrito de compras.");
        } else {
            Producto productSeleccionado = productsData.getOne(id);
            Optional<Proforma> item = proformaData.findProformaByUsuarioAndProducto(user, productSeleccionado);
            if (!item.isPresent()) {
                Proforma itemCarrito = new Proforma();
                itemCarrito.setCantidad(1);
                itemCarrito.setUser(user);
                itemCarrito.setPrecio(productSeleccionado.getPrecio());
                itemCarrito.setProduct(productSeleccionado);
                proformaData.save(itemCarrito);
                model.addAttribute("mensaje", "Se añadio el producto a tu carrito de compras.");
            } else {
                Proforma itemCarritoExistente=item.get();
                itemCarritoExistente.setCantidad(itemCarritoExistente.getCantidad()+1);
                proformaData.save(itemCarritoExistente);
                List<Proforma> count_carrito = this.proformaData.findItemsByUsuario(user);
                session.setAttribute("count_carrito", count_carrito.size());
                model.addAttribute("mensaje", "Se adiciono el producto a tu carrito de compras.");
            }
        }   
        return INDEX;
    }  

}