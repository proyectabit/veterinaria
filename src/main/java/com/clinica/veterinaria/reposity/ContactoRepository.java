package com.clinica.veterinaria.reposity;

import com.clinica.veterinaria.model.Contacto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{
    
}
