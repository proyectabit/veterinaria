package com.clinica.veterinaria.reposity;

import com.clinica.veterinaria.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    @Query(value = "SELECT o FROM Producto o WHERE o.estado='1'")
    List<Producto> getAllActiveProductos();
}