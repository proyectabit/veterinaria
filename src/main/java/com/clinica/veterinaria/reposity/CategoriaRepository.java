package com.clinica.veterinaria.reposity;

import com.clinica.veterinaria.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query(value = "SELECT o FROM Categoria o WHERE o.estado='1' ORDER BY o.id ASC")
    List<Categoria> getAllActiveCategorias();
}