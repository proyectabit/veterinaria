package com.clinica.veterinaria.reposity;

import com.clinica.veterinaria.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    @Query(value = "SELECT o FROM Producto o WHERE o.estado='1'")
    List<Producto> getAllActiveProductos();

    @Query(nativeQuery = true, value = "SELECT * FROM Producto o WHERE o.categoria = :searchCategory And o.id != :searchId And o.estado='1' ORDER BY o.id DESC LIMIT 3")
    List<Producto> getAllActiveProductosByCategory(@Param("searchCategory") Integer searchCategory, @Param("searchId") Integer searchId);
}